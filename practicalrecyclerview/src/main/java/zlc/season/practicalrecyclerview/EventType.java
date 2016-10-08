package zlc.season.practicalrecyclerview;

/**
 * Author: Season(ssseasonnn@gmail.com)
 * Date: 2016/9/28
 * Time: 14:35
 * FIXME
 */
interface EventType {
    void doSomething(SuperRecyclerView host);

    class Empty implements EventType {

        @Override
        public void doSomething(SuperRecyclerView host) {
            host.showEmptyView();
        }
    }

    class Content implements EventType {

        @Override
        public void doSomething(SuperRecyclerView host) {
            host.showContentView();
        }
    }

    class Error implements EventType {

        @Override
        public void doSomething(SuperRecyclerView host) {
            host.showErrorView();
        }
    }

    class NoMore implements EventType {

        @Override
        public void doSomething(SuperRecyclerView host) {
            host.showNoMoreView();
        }
    }

    class LoadMoreFailed implements EventType {

        @Override
        public void doSomething(SuperRecyclerView host) {
            host.showLoadMoreErrorView();
        }
    }

    class ResumeLoadMore implements EventType {

        @Override
        public void doSomething(SuperRecyclerView host) {
            host.resumeLoadMore();
        }
    }

    class AutoLoadMore implements EventType {

        @Override
        public void doSomething(SuperRecyclerView host) {
            host.autoLoadMore();
        }
    }
}
