package com.example.myapplication;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class FileUpload {
    public static class NetworkTask extends AsyncTask<Void, Void, String> {

        private String url, fileName, cookie, SUB_ID;
        private Context context;

        public NetworkTask(String url, String fileName, String cookie, String SUB_ID, Context context) {
            this.url = url;
            this.fileName = fileName;
            this.context = context;
            this.cookie = cookie;
            this.SUB_ID = SUB_ID;
        }

        @Override
        protected String doInBackground(Void... params) {
            String result; // 요청 결과를 저장할 변수.
            result = HttpURLConnection(url, "", fileName, cookie, SUB_ID); // 해당 URL로 부터 결과물을 얻어온다.
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
            //doInBackground()로 부터 리턴된 값이 onPostExecute()의 매개변수로 넘어오므로 s를 출력한다
        }
    }

    public static String HttpURLConnection(String urlString, String params, String fileName, String cookie, String SUB_ID) {
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";
        FileInputStream mFileInputStream = null;
        try {
            mFileInputStream = new FileInputStream(new File(fileName));
            URL connectUrl = new URL(urlString+";jsessionid="+cookie.substring(11,43));
            // HttpURLConnection 통신
            HttpURLConnection conn = (HttpURLConnection) connectUrl.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
            // write data
            DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
            dos.writeBytes(twoHyphens + boundary + lineEnd);
            dos.writeBytes("Content-Disposition: form-data; name=\"uploadedfile\";filename=\"" + SUB_ID + ".jpg\"" + lineEnd);
            dos.writeBytes(lineEnd);
            int bytesAvailable = mFileInputStream.available();
            int maxBufferSize = 1024;
            int bufferSize = Math.min(bytesAvailable, maxBufferSize);
            byte[] buffer = new byte[bufferSize];
            int bytesRead = mFileInputStream.read(buffer, 0, bufferSize);
            // read image
            while (bytesRead > 0) {
                dos.write(buffer, 0, bufferSize);
                bytesAvailable = mFileInputStream.available();
                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                bytesRead = mFileInputStream.read(buffer, 0, bufferSize);
            }
            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
            // close streams
            dos.flush();
            // finish upload...
            // get response
            TextDeliver GetText = new TextDeliver(conn);
            String text = GetText.GetText();
            return text;
        } catch (Exception e) {
            System.out.print(e);
            return null;
            // TODO: handle exception
        } finally {
            if(mFileInputStream != null) {
                try {
                    mFileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}