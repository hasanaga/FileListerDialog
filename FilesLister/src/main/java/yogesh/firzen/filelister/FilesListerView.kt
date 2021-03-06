package yogesh.firzen.filelister

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.File

/**
 * Created by S.Yogesh on 14-02-2016.
 */
internal class FilesListerView : RecyclerView {

    public var onFileSelectionChangedListener: OnFileSelectionChangedListener? = null
    public var onFileSelectedListener: OnFileSelectedListener? = null
    private var adapter: FileListerAdapter? = null

    val selected: File
        get() = adapter!!.selected

    var fileFilter: FileListerDialog.FILE_FILTER
        get() = adapter!!.fileFilter
        set(fileFilter) {
            adapter!!.fileFilter = fileFilter
        }

    var customExtension: String
        get() = adapter!!.customExtension
        set(extension) {
            adapter!!.customExtension = extension
        }

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle) {
        init()
    }

    private fun init() {

        layoutManager  = LinearLayoutManager(context, VERTICAL, false)
        adapter = FileListerAdapter(this)
        adapter?.onFileSelectedListener = OnFileSelectedListener { file, path ->
            //onFileSelectionListener?.onFileSelected(file, path)
            onFileSelectedListener?.onFileSelected(file, path)
        }
    }

    fun start() {
        setAdapter(adapter)
        setHasFixedSize(true)
        adapter!!.start()
    }

    fun setDefaultDir(file: File) {
        adapter!!.defaultDir = file
    }

    fun setDefaultDir(path: String) {
        setDefaultDir(File(path))
    }

    fun goToDefaultDir() {
        adapter!!.goToDefault()
    }
}
