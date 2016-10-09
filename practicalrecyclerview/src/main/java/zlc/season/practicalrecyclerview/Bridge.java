package zlc.season.practicalrecyclerview;

/**
 * Author: Season(ssseasonnn@gmail.com)
 * Date: 2016/9/28
 * Time: 14:35
 * FIXME
 */
interface Bridge {
    void doSomething(PracticalRecyclerView host);

    class Empty implements Bridge {

        @Override
        public void doSomething(PracticalRecyclerView host) {
            host.setEmptyVisible();
        }
    }

    class Content implements Bridge {

        @Override
        public void doSomething(PracticalRecyclerView host) {
            host.setContentVisibleAndResumeStatus();
        }
    }

    class Error implements Bridge {

        @Override
        public void doSomething(PracticalRecyclerView host) {
            host.setErrorVisible();
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
