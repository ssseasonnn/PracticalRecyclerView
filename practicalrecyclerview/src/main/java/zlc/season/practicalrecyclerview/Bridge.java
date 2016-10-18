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
            host.displayLoadingAndResetStatus();
        }
    }

    class Empty implements Bridge {

        @Override
        public void doSomething(PracticalRecyclerView host) {
            host.displayEmptyAndResetStatus();
        }
    }

    class Content implements Bridge {

        @Override
        public void doSomething(PracticalRecyclerView host) {
            host.displayContentAndResetStatus();
        }
    }

    class Error implements Bridge {

        @Override
        public void doSomething(PracticalRecyclerView host) {
            host.displayErrorAndResetStatus();
        }
    }

    class NoMore implements Bridge {

        @Override
        public void doSomething(PracticalRecyclerView host) {
            host.showNoMoreIfEnabled();
        }
    }

    class LoadMoreFailed implements Bridge {

        @Override
        public void doSomething(PracticalRecyclerView host) {
            host.showLoadMoreFailedIfEnabled();
        }
    }

    class ResumeLoadMore implements Bridge {

        @Override
        public void doSomething(PracticalRecyclerView host) {
            host.resumeLoadMoreIfEnabled();
        }
    }

    class AutoLoadMore implements Bridge {

        @Override
        public void doSomething(PracticalRecyclerView host) {
            host.autoLoadMoreIfEnabled();
        }
    }

    class ManualLoadMore implements Bridge {

        @Override
        public void doSomething(PracticalRecyclerView host) {
            host.manualLoadMoreIfEnabled();
        }
    }

    class SwipeConflicts implements Bridge {

        private boolean enabled;

        SwipeConflicts(boolean enabled) {
            this.enabled = enabled;
        }

        @Override
        public void doSomething(PracticalRecyclerView host) {
            host.resolveSwipeConflicts(enabled);
        }
    }
}
