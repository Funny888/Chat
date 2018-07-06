package com.example.funny.chat;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.subjects.PublishSubject;

public class RxValid {

   public static Observable<String> getValidation(@NonNull  final EditText valid)
   {
      final PublishSubject<String> subject = PublishSubject.create();
       valid.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

           }

           @Override
           public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

           }

           @Override
           public void afterTextChanged(Editable editable) {
                subject.onNext(editable.toString());
           }
       });
        return subject;
   }

}
