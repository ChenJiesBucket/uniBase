<template>
	<view class="page">
		<swiper :indicator-dots="true":autoplay="true" :interval="3000" :duration="1000"  class="carousel">
			<swiper-item>
				<image src="../../static/carousel/batmanvssuperman.png" mode="" class="carousel"></image>
			</swiper-item>
			<swiper-item>
				<image src="../../static/carousel/spiderman.png" mode="" class="carousel"></image>
			</swiper-item>
			<swiper-item>
				<image src="../../static/carousel/batmanvssuperman.png" mode="" class="carousel"></image>
			</swiper-item>
		</swiper>
	</view>
</template>

<script>
	
	export default {
		data() {
			return {
				href: 'https://uniapp.dcloud.io/component/README?id=uniui'
			}
		},
		onShow:function(){
			uni.showToast({
			    title: '标题',
			    duration: 2000,
			    icon:'none'
			})
			console.log('vuex 测试：',this.$store.state.token)
			var gd = getApp().globalData;
			gd.base.once()
			console.log('globalData 测试：',gd.base.serverUrl)
			/* base.once()
			base.once()
			console.log(base.serverUrl) */
		},
		methods: {

		}
	}
</script>

<style>
	.carousel{
		width: 100%;
		height: 440upx;
	}
</style>
