package zlc.season.practicalrecyclerview;

/**
 * Author: Season(ssseasonnn@gmail.com)
 * Date: 2016/9/28
 * Time: 14:35
 * FIXME
 */
interface Bridge {
    void doSomething(PracticalRecyclerView host);

    class Loading implements Bridge {

        @Override
        public void doSomething(PracticalRecyclerView host) {
            host.setLoadingVisibleAndResetStatus();
        }
    }

    class Empty implements Bridge {

        @Override
        public void doSomething(PracticalRecyclerView host) {
            host.setEmptyVisibleAndResetStatus();
        }
    }

    class Content implements Bridge {

        @Override
        public void doSomething(PracticalRecyclerView host) {
            host.setContentVisibleAndResetStatus();
        }
    }

    class Error implements Bridge {

        @Override
        public void doSomething(PracticalRecyclerView host) {
            host.setErrorVisibleAndResetStatus();
        }
    }

    class NoMore implements Bridge {

        @Override
        public void doSomething(PracticalRecyclerView host) {
            host.showNoMoreView();
        }
    }

    class LoadMoreFailed implements Bridge {

        @Override
        public void doSomething(PracticalRecyclerView host) {
            host.showLoadMoreFailedView();
        }
    }

    class ResumeLoadMore implements Bridge {

        @Override
        public void doSomething(PracticalRecyclerView host) {
            host.resumeLoadMore();
        }
    }

    class AutoLoadMore implements Bridge {

        @Override
        public void doSomething(PracticalRecyclerView host) {
            host.autoLoadMore();
        }
    }
}
