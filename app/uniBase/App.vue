<script>
	import base from 'common/base.js'
	export default {
		onLaunch: function() {
			 // #ifdef APP-PLUS  
			 console.log(plus.runtime.appid)
			 plus.runtime.getProperty(plus.runtime.appid, function(widgetInfo) {  
				 console.log(widgetInfo.version)
				 console.log(widgetInfo.name)
			     uni.request({  
			         url: base.serverUrl+"update/myInfo/getVersion",  
			         data: {  
			             version: widgetInfo.version,  
			             name: widgetInfo.name  
			         },  
			         success: (result) => {  
			             var data = result.data;  
			             if (data.update && data.wgtUrl) {  
			                 uni.downloadFile({  
			                     url: data.wgtUrl,  
			                     success: (downloadResult) => {  
			                         if (downloadResult.statusCode === 200) {  
			                             plus.runtime.install(downloadResult.tempFilePath, {  
			                                 force: false  
			                             }, function() {  
			                                 console.log('install success...');  
			                                 plus.runtime.restart();  
			                             }, function(e) {  
			                                 console.error('install fail...');  
			                             });  
			                         }  
			                     }  
			                 });  
			             }  
			         }  
			     });  
			 });  
			 // #endif
			console.log('App Launch')
		},
		onShow: function() {
			console.log('App Show')
		},
		onHide: function() {
			console.log('App Hide')
		},
		globalData: {  
		    base: base, 
		}, 
	}
</script>

<style>
	/*每个页面公共css */
		.page{
			width:100%;
			height:100%;
			background-color: #F7F7F7;
		}
</style>
