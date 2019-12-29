package com.yusq.videodemo.presenter;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;

import com.yusq.videodemo.imlp.IMvpView;

/**
 * 项目名：VideoDemo
 * <p>
 * 时 间：2019/10/18
 * <p>
 * 包 名：com.yusq.videodemo.presenter
 * <p>
 * 类 名：LifeCircleMvpActivity
 * <p>
 * 作 者：Yusq
 * <p>
 * 简 述：Fragment
 */
public class LifeCircleMvpFragment extends Fragment implements IMvpView {

    private MvpControler mvpControler;

    @Override
    public MvpControler getMvpControler() {
        if(this.mvpControler == null){
            this.mvpControler = new MvpControler();
        }
        return mvpControler;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if(arguments == null){
            arguments = new Bundle();
        }
        MvpControler mvpControler = this.getMvpControler();
        if(mvpControler != null){
            mvpControler.onCreate(savedInstanceState , null , arguments);
            mvpControler.onActivityCreated(savedInstanceState , null , arguments);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        MvpControler mvpControler = this.getMvpControler();
        Bundle arguments = getArguments();
        if(arguments == null){
            arguments = new Bundle();
        }
        if(mvpControler != null){
            mvpControler.onActivityCreated(savedInstanceState , null , arguments);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        MvpControler mvpControler = this.getMvpControler();
        if(mvpControler != null){
            mvpControler.onStart();
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        MvpControler mvpControler = this.getMvpControler();
        if(mvpControler != null){
            mvpControler.onResume();
        }
    }


    @Override
    public void onPause() {
        super.onPause();
        MvpControler mvpControler = this.getMvpControler();
        if(mvpControler != null){
            mvpControler.onPause();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        MvpControler mvpControler = this.getMvpControler();
        if(mvpControler != null){
            mvpControler.onStop();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        MvpControler mvpControler = this.getMvpControler();
        if(mvpControler != null){
            mvpControler.onViewDestroy();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        MvpControler mvpControler = this.getMvpControler();
        if(mvpControler != null){
            mvpControler.onDestroy();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        MvpControler mvpControler = this.getMvpControler();
        if(mvpControler != null){
            mvpControler.onSaveInstanceState(outState);
        }
    }

}
