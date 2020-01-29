package fr.skyhook.sample.scenemanager

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import io.skyhook.sample.scenemanager.R
import io.skyhook.scenemanager.SceneManager
import io.skyhook.scenemanager.annotations.BuildScenes
import io.skyhook.scenemanager.annotations.Scene
import kotlinx.android.synthetic.main.placeholder.view.*
import kotlinx.android.synthetic.main.sample_view_main.view.*
import kotlinx.android.synthetic.main.spinner.view.*

@BuildScenes(
    value = [
        Scene(id = Scene.MAIN, layout = R.layout.sample_view_main),
        Scene(id = Scene.SPINNER, layout = R.layout.spinner),
        Scene(id = Scene.PLACEHOLDER, layout = R.layout.placeholder)
    ],
    first = Scene.MAIN
)
class SampleView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr), View.OnClickListener {

    init {
        SceneManager.create(this)

        sample_switch_to_main.setOnClickListener(this)
        sample_switch_to_progress.setOnClickListener(this)
        go_to_main_from_loader.setOnClickListener(this)
        sample_switch_to_placeholder.setOnClickListener(this)
        go_to_main_from_placeholder.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.sample_switch_to_main,
            R.id.go_to_main_from_loader,
            R.id.go_to_main_from_placeholder -> SceneManager.scene(this, Scene.MAIN)
            R.id.sample_switch_to_progress -> SceneManager.scene(this, Scene.SPINNER)
            R.id.sample_switch_to_placeholder -> SceneManager.scene(this, Scene.PLACEHOLDER)
            else -> throw IllegalArgumentException("Nope")
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        SceneManager.release(this)
    }
}
