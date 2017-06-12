/**
 * Copyright 2015-2016 Xiaofei
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package xiaofei.library.datastoragetest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import xiaofei.library.datastorage.DataStorageFactory;
import xiaofei.library.datastorage.IDataStorage;
import xiaofei.library.datastorage.annotation.ClassId;
import xiaofei.library.datastorage.annotation.ObjectId;

public class MainActivity extends AppCompatActivity {
    IDataStorage dataStorage = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String url = "岑参（cén shēn）（约715年—770年），汉族，原籍南阳（今属河南新野），迁居江陵（今属湖北），是唐代著名的边塞诗人，去世之时56岁。其诗歌富有浪漫主义的特色，气势雄伟，想象丰富，色彩瑰丽，热情奔放，尤其擅长七言歌行。现存诗403首，七十多首边塞诗，另有《感旧赋》一篇，《招北客文》一篇，墓铭两篇。 ";
        Log.e("cody", "len:" + url.length());
        dataStorage = DataStorageFactory.getInstance(this, DataStorageFactory.TYPE_DATABASE);
        findViewById(R.id.store).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                List<B> list = new ArrayList<B>();
//                list.add(new B(new A("abc"), new BigDecimal("0.9")));
//                list.add(new B(new A("xyz"), new BigDecimal("90")));
//                dataStorage.storeOrUpdate(list);
//                dataStorage.storeOrUpdate(new C(), "1");
                storeThread();
            }
        });
        findViewById(R.id.load).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadThread();
//                List<B> list = dataStorage.loadAll(B.class);
//                StringBuilder sb = new StringBuilder();
//                for (B b : list) {
//                    sb.append(b.a.s).append(' ').append(b.bigDecimal.toString()).append(' ');
//                }
//                sb.append('\n');
//                C c = dataStorage.load(C.class, "1");
//                sb.append(c.i).append("length=" + c.s.length());
//                Toast.makeText(getApplicationContext(), sb.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void storeThread() {
        new Thread() {
            @Override
            public void run() {
                long start = new Date().getTime();
                Log.e("cody", "start:" + start);
                for (int i = 0; i < 400; i++) {
                    Msg msg = new Msg("" + i, "岑参（cén shēn）（约715年—770年），汉族，原籍南阳（今属河南新野），" +
                            "迁居江陵（今属湖北），是唐代著名的边塞诗人，去世之时56岁。其诗歌富有浪漫主义的特色，气势雄伟，" +
                            "想象丰富，色彩瑰丽，热情奔放，尤其擅长七言歌行。现存诗403首，七十多首边塞诗，另有《感旧赋》一篇，《招北客文》一篇，墓铭两篇。 ",
                            new Date().getTime() + "", "www.baidu.com+This simple example parses a JSON string into a document (DOM), " +
                            "make a simple modification of the DOM, and finally stringify the DOM to a JSON string.");
                    dataStorage.storeOrUpdate(msg, msg.getMsgId());
                }
                long end = new Date().getTime();
                Log.e("cody", "time:" + (end - start));
            }
        }.start();
    }

    private void loadThread() {
        new Thread() {
            @Override
            public void run() {
                long start = new Date().getTime();
                Log.e("cody", "load start:" + start);
                List<Msg> msgList = dataStorage.loadAll(Msg.class);
                long end = new Date().getTime();
                Log.e("cody", "load end:" + (end - start));
                int count = 0;
                for (Msg msg : msgList) {
                    Log.e("cody", msg.toString());
                    count++;
                }
                Log.e("cody", "load time:" + (end - start) + ";count:" + count);
            }
        }.start();
    }


    @ClassId("msg")
    private static class Msg {
        @ObjectId
        private String msgId;
        private String msg;
        private String time;
        private String url;

        public Msg() {

        }

        public Msg(String msgId, String msg, String time, String url) {
            this.msgId = msgId;
            this.msg = msg;
            this.time = time;
            this.url = url;
        }

        public String getMsgId() {
            return msgId;
        }

        public void setMsgId(String msgId) {
            this.msgId = msgId;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        @Override
        public String toString() {
            return "msgid:" + msgId + ";msg:" + msg + ";time:" + time + ";url:" + url;
        }
    }

    private static class A {
        String s = "Test";

        A(String s) {
            this.s = s;
        }
    }

    private static class B {
        A a;
        BigDecimal bigDecimal;

        @ObjectId
        public String get() {
            return a.s;
        }

        B(A a, BigDecimal bigDecimal) {
            this.a = a;
            this.bigDecimal = bigDecimal;
        }
    }

    private static class C {
        public int i;
        public String s;

        public C() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 100000; ++i) {
                sb.append(Integer.toString(i));
            }
            s = sb.toString();
            this.i = s.length();
        }
    }
}
