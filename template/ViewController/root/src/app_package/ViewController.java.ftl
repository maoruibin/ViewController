package ${packageName};

import android.content.Context;
import android.view.View;

import name.gudong.viewcontroller.ViewController;

public class ${viewControllerClass} extends ViewController<${dataType}> {

    public ${viewControllerClass}(Context context) {
        super(context);
    }

    @Override
    protected int resLayoutId() {
        return 0;
    }

    @Override
    protected void onCreatedView(View view) {

    }

    @Override
    protected void onBindView(${dataType} data) {

    }
}
