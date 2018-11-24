package com.example.harshit.wifiapp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.IntentService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.harshit.wifiapp.data.DatabaseClient;
import com.example.harshit.wifiapp.data.WifiSignal;
import com.example.harshit.wifiapp.data.WifiSignalDao;

public class MainActivity extends Activity {

    private WifiManager mainWifi;
    private WifiReceiver receiverWifi;
    private Button btnRefresh;
    private Button capture;
    private Executor executor;
    ListAdapter adapter;
    ListView lvWifiDetails;
    List<ScanResult> wifiList;
    private WifiSignalDao dao;
    private Context context;
    int x1 = 0, x2 = 0, x3 = 0, x4 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        lvWifiDetails = (ListView) findViewById(R.id.lvWifiDetails);
        btnRefresh = (Button) findViewById(R.id.btnRefresh);
        executor = Executors.newSingleThreadExecutor();
        capture = findViewById(R.id.capture);
        dao = DatabaseClient.getInstance(this).getAppDatabase().wifiSignalDao();

        mainWifi = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        receiverWifi = new WifiReceiver();
        registerReceiver(receiverWifi, new IntentFilter(
                WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        scanWifiList();

        btnRefresh.setOnClickListener(v -> {
            scanWifiList();
            Log.i("ts", "test");
        });

        capture.setOnClickListener(this::onClick);
    }

    public void onClick(View view) {
        x1 = x2 = x3 = x4 = 0;
        Handler handler = new Handler();
        List<WifiSignal> list = new ArrayList<>();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                WifiSignal signal = captureResult();
                Log.d("CURRENT READING", signal.toString());
                x1 += signal.getLevel1();
                x2 += signal.getLevel2();
                x3 += signal.getLevel3();
                x4 += signal.getLevel4();
                list.add(signal);
                if (list.size() == 20) {
                    WifiSignal wifisignal = new WifiSignal();
                    wifisignal.setLevel1(x1 / 20);
                    wifisignal.setLevel2(x2 / 20);
                    wifisignal.setLevel3(x3 / 20);
                    wifisignal.setLevel4(x4 / 20);
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Enter Grid:");
                    EditText editText = new EditText(context);
                    builder.setView(editText);
                    builder.setPositiveButton("OK", (dialogInterface, i) -> {
                        int grid = Integer.parseInt(editText.getText().toString());
                        wifisignal.setGrid(grid);
                        executor.execute(() -> dao.insert(wifisignal));
                    });
                    builder.show();
                    handler.removeCallbacks(this);
                } else {
                    handler.postDelayed(this, 1000);
                }
            }
        }, 1000);
    }

    private void setAdapter(List<ScanResult> devices) {
        adapter = new ListAdapter(getApplicationContext(), devices);
        Log.i("ts", "size:" + devices.size());
        lvWifiDetails.setAdapter(adapter);
    }

    private void scanWifiList() {
        HashMap<Integer, Integer> dists = new HashMap<>();
        mainWifi.startScan();
        wifiList = mainWifi.getScanResults();
        List<ScanResult> filtered = filterResults(wifiList);
        WifiSignal signal = getWifiSignal(filtered);
        setAdapter(filtered);
        executor.execute(() -> {
            List<WifiSignal> list = dao.getAll();
            for (WifiSignal w : list) {
                Log.d("DB ENTRY", w.toString());
                int d1 = (int) Math.pow(signal.getLevel1() - w.getLevel1(), 2);
                int d2 = (int) Math.pow(signal.getLevel2() - w.getLevel2(), 2);
                int d3 = (int) Math.pow(signal.getLevel3() - w.getLevel3(), 2);
                int d4 = (int) Math.pow(signal.getLevel4() - w.getLevel4(), 2);
                int dist = d1 + d2 + d3 + d4;
                dists.put(dist, w.getGrid());
            }
            if (list.size() > 0) {
                int min = Collections.min(dists.keySet());
                Log.d("PREDICTION", String.valueOf(dists.get(min)));
                runOnUiThread(() -> {
                    Toast.makeText(this, "Prediction: " + dists.get(min), Toast.LENGTH_SHORT).show();
                });
            }
        });
    }

    private WifiSignal captureResult() {
        mainWifi.startScan();
        wifiList = mainWifi.getScanResults();
        List<ScanResult> filtered = filterResults(wifiList);
        WifiSignal wifiSignal = new WifiSignal();
        for (ScanResult s : filtered) {
            switch (s.SSID) {
                case Constants.device1:
                    wifiSignal.setSsid1(s.SSID);
                    wifiSignal.setLevel1(s.level);
                    break;

                case Constants.device2:
                    wifiSignal.setSsid2(s.SSID);
                    wifiSignal.setLevel2(s.level);
                    break;

                case Constants.device3:
                    wifiSignal.setSsid3(s.SSID);
                    wifiSignal.setLevel3(s.level);
                    break;

                case Constants.device4:
                    wifiSignal.setSsid4(s.SSID);
                    wifiSignal.setLevel4(s.level);
                    break;
            }
        }
        return wifiSignal;
    }

    private WifiSignal getWifiSignal(List<ScanResult> wifiList) {
        WifiSignal wifiSignal = new WifiSignal();
        for (ScanResult s : wifiList) {
            switch (s.SSID) {
                case Constants.device1:
                    wifiSignal.setSsid1(s.SSID);
                    wifiSignal.setLevel1(s.level);
                    break;

                case Constants.device2:
                    wifiSignal.setSsid2(s.SSID);
                    wifiSignal.setLevel2(s.level);
                    break;

                case Constants.device3:
                    wifiSignal.setSsid3(s.SSID);
                    wifiSignal.setLevel3(s.level);
                    break;

                case Constants.device4:
                    wifiSignal.setSsid4(s.SSID);
                    wifiSignal.setLevel4(s.level);
                    break;
            }
        }
        return wifiSignal;
    }

    private List<ScanResult> filterResults(List<ScanResult> wifiList) {
        List<ScanResult> filtered = new ArrayList<>();
        Constants constants = new Constants();
        for (ScanResult s : wifiList) {
            if (constants.devices.contains(s.SSID)) {
                filtered.add(s);
            }
        }

        return filtered;
    }

    class WifiReceiver extends BroadcastReceiver {
        public void onReceive(Context c, Intent intent) {
        }
    }
}
