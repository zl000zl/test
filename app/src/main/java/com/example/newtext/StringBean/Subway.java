package com.example.newtext.StringBean;

import java.util.List;

public class Subway {
    /**
     * lineId : 31
     * lineName : 2号线
     * preStep : {"name":"朝阳门","lines":[{"lineId":21,"lineName":"6号线"},{"lineId":24,"lineName":"6号线"},{"lineId":31,"lineName":"2号线"},{"lineId":38,"lineName":"2号线"}]}
     * nextStep : {"name":"北京站","lines":[{"lineId":31,"lineName":"2号线"},{"lineId":38,"lineName":"2号线"}]}
     * currentName : 建国门
     * reachTime : 0
     */

    private int lineId;
    private String lineName;
    private PreStepBean preStep;
    private NextStepBean nextStep;
    private String currentName;
    private int reachTime;

    public int getLineId() {
        return lineId;
    }

    public void setLineId(int lineId) {
        this.lineId = lineId;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public PreStepBean getPreStep() {
        return preStep;
    }

    public void setPreStep(PreStepBean preStep) {
        this.preStep = preStep;
    }

    public NextStepBean getNextStep() {
        return nextStep;
    }

    public void setNextStep(NextStepBean nextStep) {
        this.nextStep = nextStep;
    }

    public String getCurrentName() {
        return currentName;
    }

    public void setCurrentName(String currentName) {
        this.currentName = currentName;
    }

    public int getReachTime() {
        return reachTime;
    }

    public void setReachTime(int reachTime) {
        this.reachTime = reachTime;
    }

    public static class PreStepBean {
        /**
         * name : 朝阳门
         * lines : [{"lineId":21,"lineName":"6号线"},{"lineId":24,"lineName":"6号线"},{"lineId":31,"lineName":"2号线"},{"lineId":38,"lineName":"2号线"}]
         */

        private String name;
        private List<LinesBean> lines;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<LinesBean> getLines() {
            return lines;
        }

        public void setLines(List<LinesBean> lines) {
            this.lines = lines;
        }

        public static class LinesBean {
            /**
             * lineId : 21
             * lineName : 6号线
             */

            private int lineId;
            private String lineName;

            public int getLineId() {
                return lineId;
            }

            public void setLineId(int lineId) {
                this.lineId = lineId;
            }

            public String getLineName() {
                return lineName;
            }

            public void setLineName(String lineName) {
                this.lineName = lineName;
            }
        }
    }

    public static class NextStepBean {
        /**
         * name : 北京站
         * lines : [{"lineId":31,"lineName":"2号线"},{"lineId":38,"lineName":"2号线"}]
         */

        private String name;
        private List<LinesBeanX> lines;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<LinesBeanX> getLines() {
            return lines;
        }

        public void setLines(List<LinesBeanX> lines) {
            this.lines = lines;
        }

        public static class LinesBeanX {
            /**
             * lineId : 31
             * lineName : 2号线
             */

            private int lineId;
            private String lineName;

            public int getLineId() {
                return lineId;
            }

            public void setLineId(int lineId) {
                this.lineId = lineId;
            }

            public String getLineName() {
                return lineName;
            }

            public void setLineName(String lineName) {
                this.lineName = lineName;
            }
        }
    }
}
