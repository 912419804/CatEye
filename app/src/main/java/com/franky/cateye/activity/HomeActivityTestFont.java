package com.franky.cateye.activity;

import com.franky.cateye.base.CatActivity;

/**
 * 仿开眼文字顺序出现效果(需完善)
 */

public class HomeActivityTestFont extends CatActivity {
//
//    @BindView(R.id.tv_test)
//    TextView tv_test;
//    @BindView(R.id.tv_test2)
//    TextView tv_test2;
//    @BindView(R.id.tv_test3)
//    TextView tv_test3;
//
//    String text = "通过效果图可以看到两个activity参加动画有一个ImageView和一个TextView，那么这种多个view协作的动画怎么实现呢？我们通过代码来了解一下，首先是两个activity的布局文件,第一个activity";
//    StringBuilder sb = new StringBuilder(text.length());
//    StringBuilder sb2 = new StringBuilder(text.length());
//    StringBuilder sb3 = new StringBuilder(text.length());
//    int index;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_home);
//        mHandler.sendEmptyMessage(1);
//        mHandler.sendEmptyMessage(2);
//        mHandler.sendEmptyMessage(3);
////        CatLog.d("hello");
////        CatLog.e("hello");
////        CatLog.w("hello");
////        CatLog.v("hello");
////        CatLog.wtf("hello");
////        CatLog.json("{'abc':'123'}");
////        CatLog.xml("<A><b>xml test</b></A>");
////        CatLog.log(Logger.DEBUG, "tag", "message", new Throwable());
//
//
//    }
//
//    @Override
//    protected void handleMessage(Message msg) {
//        super.handleMessage(msg);
//        switch (msg.what) {
//            case 1:
//                if (index < text.length()) {
//                    sb.append(text.charAt(index));
//                    index++;
//                    tv_test.setText(sb.toString());
//                    mHandler.sendEmptyMessage(msg.what);
//                }
//                break;
//            case 2:
//                if (index < text.length()) {
//                    sb2.append(text.charAt(index));
//                    index++;
//                    tv_test2.setText(sb.toString());
//                    mHandler.sendEmptyMessage(msg.what);
//                }
//                break;
//            case 3:
//                if (index < text.length()) {
//                    sb3.append(text.charAt(index));
//                    index++;
//                    tv_test3.setText(sb.toString());
//                    mHandler.sendEmptyMessage(msg.what);
//                }
//                break;
//        }
//    }
//
//    @Override
//    protected void onNewIntent(Intent intent) {
//        super.onNewIntent(intent);
//        if (intent != null) {
//            //获取退出标记,来退出app
//            boolean isExit = intent.getBooleanExtra(TAG_EXIT, false);
//            if (isExit) {
//                this.finish();
//            }
//        }
//    }
//
//    private boolean mIsExit;
//
//    @Override
//    /** * 双击返回键退出 */
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            if (mIsExit) {
//                this.finish();
//            } else {
//                show("再按一次退出");
//                mIsExit = true;
//                mHandler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        mIsExit = false;
//                    }
//                }, 2000);
//            }
//            return true;
//        }
//        return super.onKeyDown(keyCode, event);
//    }
}
