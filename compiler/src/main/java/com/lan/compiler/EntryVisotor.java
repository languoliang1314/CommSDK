package com.lan.compiler;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Modifier;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.SimpleAnnotationValueVisitor7;

public class EntryVisotor extends SimpleAnnotationValueVisitor7<Void, Void> {
    private String mPackageName;
    private TypeMirror mTypeMirror;
    private Filer mFiler;
    @Override
    public Void visitString(String s, Void aVoid) {
        mPackageName = s;
        return aVoid;
    }

    @Override
    public Void visitType(TypeMirror typeMirror, Void aVoid) {
        mTypeMirror = typeMirror;
        generateWXSignInCode();
        generateWXPayCode();
        return aVoid;
    }
    public void setFiler(Filer filer){
        this.mFiler = filer;
    }

    /**
     * 生成类
     */
    private void generateWXSignInCode() {
        TypeSpec.Builder classTypeSpec = TypeSpec.classBuilder("WXEntryActivity")
                .addModifiers(Modifier.PUBLIC,Modifier.FINAL)
                .superclass(TypeName.get(mTypeMirror));
        try {
            JavaFile.builder(mPackageName+"wxapi",classTypeSpec.build())
                    .addFileComment("微信登录自动生成")
                    .build().writeTo(mFiler);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /**
     * 生成类
     */
    private void generateWXPayCode() {
        //xxx.wxapi.WXEntryActivity extends BaseWXEntryActivity
        TypeSpec.Builder classTypeSpec = TypeSpec.classBuilder("WXPayEntryActivity")
                .addModifiers(Modifier.PUBLIC,Modifier.FINAL)
                .superclass(TypeName.get(mTypeMirror));
        try {
            JavaFile.builder(mPackageName+"wxapi",classTypeSpec.build())
                    .addFileComment("微信支付自动生成")
                    .build().writeTo(mFiler);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
