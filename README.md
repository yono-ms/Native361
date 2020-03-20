# Native361
Native Android Application

# Logger

Base XXX

    val logger: Logger by lazy {
        LoggerFactory.getLogger(javaClass.simpleName)
    }

Baseを持たない場合は独自にインスタンス化する。
    
# Activity View Model

オンメモリ共有を作る。

- 画面遷移をキック
- アラートをキック
- 画面間共有領域

## 手順

1. ViewModel定義
1. Activityでインスタンス化して監視
1. Fragmentでインスタンス化
1. Fragment View Modelに参照を渡す

## Base Fragment

Activity View Modelをインスタンス化する。

## Base View Model

Activity View Modelの参照を持つ。

## Fragmentからの参照

Base Fragment

    protected val appViewModel: AppViewModel by lazy {
        ViewModelProvider(activity!!)[AppViewModel::class.java].apply {
            dialogMessage.observe(viewLifecycleOwner, Observer {
                it?.let {
                    logger.info(it.toString())
                    val dialog = AlertDialogFragment.newInstance(it, this@BaseFragment)
                    dialog.show(activity!!.supportFragmentManager, "alert")
                    dialogMessage.value = null
                }
            })
        }
    }

## Fragment View Modelに渡す

XXX Fragment

    private val viewModel: HomeViewModel by lazy {
        ViewModelProvider(this)[HomeViewModel::class.java].also {
            it.appViewModel = appViewModel
        }
    }

# Adapter

bindingを使用しない方向で努力する。
itemsはFragment View Modelで持ち、observerでadapterに連携する。

# Dialog

bindingを使用しない方向で努力する。
動的コンテンツを表示する場合はDialogではなくFragmentをaddして通常画面として作る。
