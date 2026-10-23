package com.my.tools;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends Activity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        webView = new WebView(this);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        
        // ربط الجافا سكريبت مع الأندرويد للتوجيه المباشر
        webView.addJavascriptInterface(new WebAppInterface(this), "AndroidBridge");

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.startsWith("https://t.me/") || url.startsWith("tg://") || url.startsWith("intent://")) {
                    try {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(intent);
                        return true;
                    } catch (Exception e) {
                        return false;
                    }
                }
                return false;
            }
        });

        // واجهة الأدوات الخمسة باللون الأسود والأخضر الفخم
        String htmlContent = "<!DOCTYPE html>\n" +
                "<html lang=\"ar\" dir=\"rtl\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no\">\n" +
                "    <title>مكتبة أدوات بايثون - AZ TOOLS</title>\n" +
                "    <link href=\"https://fonts.googleapis.com/css2?family=Tajawal:wght@500;700;800&display=swap\" rel=\"stylesheet\">\n" +
                "    <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css\">\n" +
                "    <style>\n" +
                "        :root {\n" +
                "            --bg-main: #050807;\n" +
                "            --bg-card: #0d1410;\n" +
                "            --neon-green: #00ff66;\n" +
                "            --text-color: #e2f1e8;\n" +
                "            --border-color: #1a2e22;\n" +
                "        }\n" +
                "        body {\n" +
                "            background-color: var(--bg-main);\n" +
                "            color: var(--text-color);\n" +
                "            font-family: 'Tajawal', sans-serif;\n" +
                "            margin: 0;\n" +
                "            padding: 16px;\n" +
                "        }\n" +
                "        .header {\n" +
                "            background: var(--bg-card);\n" +
                "            padding: 18px;\n" +
                "            border-radius: 16px;\n" +
                "            border: 1px solid var(--neon-green);\n" +
                "            box-shadow: 0 0 15px rgba(0, 255, 102, 0.15);\n" +
                "            text-align: center;\n" +
                "            margin-bottom: 18px;\n" +
                "        }\n" +
                "        .header h1 {\n" +
                "            margin: 0;\n" +
                "            color: var(--neon-green);\n" +
                "            font-size: 22px;\n" +
                "            text-shadow: 0 0 10px rgba(0, 255, 102, 0.4);\n" +
                "        }\n" +
                "        .header p {\n" +
                "            color: #8fae9c;\n" +
                "            font-size: 12px;\n" +
                "            margin-top: 5px;\n" +
                "        }\n" +
                "        .tg-banner {\n" +
                "            background: linear-gradient(135deg, #0d1410 0%, #122419 100%);\n" +
                "            border: 1px solid var(--neon-green);\n" +
                "            border-radius: 14px;\n" +
                "            padding: 14px;\n" +
                "            display: flex;\n" +
                "            align-items: center;\n" +
                "            justify-content: space-between;\n" +
                "            margin-bottom: 18px;\n" +
                "        }\n" +
                "        .tg-info {\n" +
                "            display: flex;\n" +
                "            align-items: center;\n" +
                "            gap: 10px;\n" +
                "        }\n" +
                "        .tg-info i {\n" +
                "            font-size: 26px;\n" +
                "            color: var(--neon-green);\n" +
                "        }\n" +
                "        .section-title {\n" +
                "            font-size: 15px;\n" +
                "            color: var(--neon-green);\n" +
                "            margin-bottom: 10px;\n" +
                "            font-weight: 700;\n" +
                "            display: flex;\n" +
                "            align-items: center;\n" +
                "            gap: 8px;\n" +
                "        }\n" +
                "        .card {\n" +
                "            background: var(--bg-card);\n" +
                "            border: 1px solid var(--border-color);\n" +
                "            padding: 15px;\n" +
                "            border-radius: 14px;\n" +
                "            margin-bottom: 14px;\n" +
                "        }\n" +
                "        .card-title {\n" +
                "            font-weight: bold;\n" +
                "            font-size: 15px;\n" +
                "            color: #ffffff;\n" +
                "            display: flex;\n" +
                "            align-items: center;\n" +
                "            gap: 8px;\n" +
                "            margin-bottom: 8px;\n" +
                "        }\n" +
                "        .card-title i {\n" +
                "            color: var(--neon-green);\n" +
                "        }\n" +
                "        .card-desc {\n" +
                "            font-size: 11px;\n" +
                "            color: #8fae9c;\n" +
                "            line-height: 1.5;\n" +
                "            margin-bottom: 12px;\n" +
                "        }\n" +
                "        .btn-group {\n" +
                "            display: flex;\n" +
                "            gap: 8px;\n" +
                "        }\n" +
                "        .btn {\n" +
                "            flex: 1;\n" +
                "            background: var(--neon-green);\n" +
                "            color: #000000;\n" +
                "            padding: 9px;\n" +
                "            border-radius: 8px;\n" +
                "            text-align: center;\n" +
                "            text-decoration: none;\n" +
                "            font-weight: 800;\n" +
                "            font-size: 12px;\n" +
                "            border: none;\n" +
                "            cursor: pointer;\n" +
                "            box-shadow: 0 0 8px rgba(0, 255, 102, 0.2);\n" +
                "        }\n" +
                "        .btn-outline {\n" +
                "            background: transparent;\n" +
                "            color: var(--neon-green);\n" +
                "            border: 1px solid var(--neon-green);\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div class=\"header\">\n" +
                "        <h1><i class=\"fa-solid fa-terminal\"></i> مكتبة أدوات Python</h1>\n" +
                "        <p>اضغط على الأداة للتشغيل المباشر عبر البايثون</p>\n" +
                "    </div>\n" +
                "\n" +
                "    <div class=\"tg-banner\">\n" +
                "        <div class=\"tg-info\">\n" +
                "            <i class=\"fa-brands fa-telegram\"></i>\n" +
                "            <div>\n" +
                "                <div style=\"font-weight: 800; font-size: 14px;\">قناتنا الرسمية</div>\n" +
                "                <div style=\"font-size: 11px; color: #8fae9c;\">@az_4oi</div>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <a href=\"https://t.me/az_4oi\" class=\"btn\" style=\"flex: unset; padding: 6px 14px;\">انضمام</a>\n" +
                "    </div>\n" +
                "\n" +
                "    <div class=\"section-title\">\n" +
                "        <i class=\"fa-solid fa-code\"></i> الأدوات الخمسة الأساسية\n" +
                "    </div>\n" +
                "\n" +
                "    <!-- 1. حسابات ببجي بايثون -->\n" +
                "    <div class=\"card\">\n" +
                "        <div class=\"card-title\">\n" +
                "            <i class=\"fa-solid fa-gamepad\"></i> أداة حسابات ببجي بايثون\n" +
                "        </div>\n" +
                "        <div class=\"card-desc\">\n" +
                "            فحص وتحليل حسابات ببجي وإرسال النتائج للبوت.\n" +
                "        </div>\n" +
                "        <div class=\"btn-group\">\n" +
                "            <button class=\"btn\" onclick=\"runTool('pubg')\">تشغيل عبر البايثون</button>\n" +
                "            <a href=\"https://t.me/hq1y0\" class=\"btn btn-outline\">دعم فني</a>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "\n" +
                "    <!-- 2. تيك توك -->\n" +
                "    <div class=\"card\">\n" +
                "        <div class=\"card-title\">\n" +
                "            <i class=\"fa-brands fa-tiktok\"></i> أداة تيك توك\n" +
                "        </div>\n" +
                "        <div class=\"card-desc\">\n" +
                "            فحص وفلترة متاحات ومعلومات حسابات تيك توك.\n" +
                "        </div>\n" +
                "        <div class=\"btn-group\">\n" +
                "            <button class=\"btn\" onclick=\"runTool('tiktok')\">تشغيل عبر البايثون</button>\n" +
                "            <a href=\"https://t.me/hq1y0\" class=\"btn btn-outline\">دعم فني</a>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "\n" +
                "    <!-- 3. جلب معلومات حساب انستغرام -->\n" +
                "    <div class=\"card\">\n" +
                "        <div class=\"card-title\">\n" +
                "            <i class=\"fa-brands fa-instagram\"></i> جلب معلومات حساب انستغرام\n" +
                "        </div>\n" +
                "        <div class=\"card-desc\">\n" +
                "            استخراج كافة تفاصيل ومعلومات الحسابات بدقة عالية.\n" +
                "        </div>\n" +
                "        <div class=\"btn-group\">\n" +
                "            <button class=\"btn\" onclick=\"runTool('insta_info')\">تشغيل عبر البايثون</button>\n" +
                "            <a href=\"https://t.me/hq1y0\" class=\"btn btn-outline\">دعم فني</a>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "\n" +
                "    <!-- 4. يوزرات انستغرام مميزة -->\n" +
                "    <div class=\"card\">\n" +
                "        <div class=\"card-title\">\n" +
                "            <i class=\"fa-solid fa-fire\"></i> يوزرات انستغرام مميزة\n" +
                "        </div>\n" +
                "        <div class=\"card-desc\">\n" +
                "            صيد وتوليد يوزرات انستا مميزة بأنماط متعددة.\n" +
                "        </div>\n" +
                "        <div class=\"btn-group\">\n" +
                "            <button class=\"btn\" onclick=\"runTool('insta_users')\">تشغيل عبر البايثون</button>\n" +
                "            <a href=\"https://t.me/hq1y0\" class=\"btn btn-outline\">دعم فني</a>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "\n" +
                "    <!-- 5. فيس ربط ألعاب -->\n" +
                "    <div class=\"card\">\n" +
                "        <div class=\"card-title\">\n" +
                "            <i class=\"fa-brands fa-facebook\"></i> أداة فيس ربط ألعاب\n" +
                "        </div>\n" +
                "        <div class=\"card-desc\">\n" +
                "            فحص حسابات الفيس بوك المرتبطة بالألعاب.\n" +
                "        </div>\n" +
                "        <div class=\"btn-group\">\n" +
                "            <button class=\"btn\" onclick=\"runTool('facebook')\">تشغيل عبر البايثون</button>\n" +
                "            <a href=\"https://t.me/hq1y0\" class=\"btn btn-outline\">دعم فني</a>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "\n" +
                "    <script>\n" +
                "        function runTool(toolName) {\n" +
                "            AndroidBridge.openPythonTool(toolName);\n" +
                "        }\n" +
                "    </script>\n" +
                "</body>\n" +
                "</html>";

        webView.loadDataWithBaseURL(null, htmlContent, "text/html", "UTF-8", null);
        setContentView(webView);
    }

    public static class WebAppInterface {
        private Context mContext;

        WebAppInterface(Context c) {
            mContext = c;
        }

        @JavascriptInterface
        public void openPythonTool(String toolName) {
            Toast.makeText(mContext, "جاري فتح تطبيق Pydroid 3...", Toast.LENGTH_SHORT).show();
            try {
                // استخدام Intent مباشر يتخطى قيود الأندرويد الحديثة ويفتح البايثون فوراً
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.setClassName("ru.iiec.pydroid3", "ru.iiec.pydroid3.MainActivity");
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            } catch (Exception e) {
                try {
                    // طريقة بديلة عن طريق رابط التطبيق في المتجر في حال تعذر الإطلاق المباشر
                    Intent marketIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=ru.iiec.pydroid3"));
                    marketIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(marketIntent);
                } catch (Exception ex) {
                    Toast.makeText(mContext, "عذراً، يرجى فتح تطبيق Pydroid 3 يدوياً", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
