package hasancanzubaroglu.simpleapplocker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    /**
     * Bu ekran activity'i baslatacak. Oncreate metodunda dogrusan servis baslayacak ve her 100 milisaniyede bir
     * onplandaki uygulamayi kontrol edecek. Bir onceki degerden farkli gordugu an kendi yazdigimiz BroadCastReceiveri
     * calistiracak. Hiz kazanmak acisindan uygulamalar database ya da sharedPreferences uzerinde tutulmayacak.
     * Sifrelenen uygulamalar servis uzerinde cash edilecek.
     *
     * ONEMLI NOT: Bu yontemin performans analizi henuz yapilmadi. Duruma gore yeni cozumler bulunabilir. Ancak
     * Sifrelenen uygulamanin 100 milisaniye icerisinde get edilecek olmasi onemli. Uygulama ikonu cash edilmese de
     * olur. Paket adi ve ismini cashlariz, komut geldiginde direk paket adini karsilastiricak ve aktiviteye App
     * objesini paslayacak. Bu sirada uygulama ikonunun get edilmesi icin ARAYUZDEN BAGIMSIZ bir AsycnTask ya da
     * servis calisabilir. Ancak UI Thread dan bagimsiz olmasi gercekten onemli. Sifre de SIFRELENMEMIS olarak
     * java class uzeride tutulacak. Bu hizimizi onemli olcude arttirir. Simdilik bu kadar Tesekkurler.
     */
    Button btnService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnService = (Button) findViewById(R.id.button);

        btnService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startService(new Intent(MainActivity.this, MyService.class));
            }
        });
    }
}
