var vu = new Vue({
    el:"#app",
    data:{
        confirmEndTime:null,
        confirmStartTime:null
    },
    computed:{
        test:{
            get(){
                var times=[];
                if(this.confirmStartTime!=null)
                    times.push(this.confirmStartTime);
                if(this.confirmEndTime!=null)
                    times.push(this.confirmEndTime);
                return times;
            },
            set(newValue) {
                console.log(newValue);
                this.confirmStartTime = newValue[0];
                this.confirmEndTime = newValue[1];
            }
        }
    },
    updated(){
        console.log("update...")
    }
});
