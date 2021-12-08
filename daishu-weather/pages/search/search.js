import Request from '../../utils/request.js'
import * as Type from '../../server/type.js'

const app = getApp();
Page({
  data: {
    isSearch: false,
    result: [],
    value: '',
    hotCity: app.globalData.hotCity,
    history: []
  },
  inputHandle(e) {
    const {
      value
    } = e.detail
    console.log("value=", value);
    if (value) {
      const data = {
        "keyword": value,
        "region": "全国"
      }
      Request({
        url: Type.SuggestionGeocoder,
        data,
        success: (res) => {
          console.log("SuggestionGeocoder=", res)
          if (res.data.code == "200") {
            this.setData({
              isSearch: true,
              result: res.data.result,
            })
          } else {
            console.log("SuggestionGeocoder异常=", res)
          }
        }
      })
    }
  },
  onLoad() {
    this.setData({
      history: this.getLocal()
    })
  },
  cancelHandle() {
    wx.navigateBack()
  },
  selectItem(e) {
    const {
      lat,
      lng
    } = e.currentTarget.dataset
    wx.navigateTo({
      url: `../weather/weather?lat=${lat}&lng=${lng}`
    })
  },
  clearHandle() {
    this.setData({
      value: ''
    })
  },
  clearHistory() {
    wx.removeStorage({
      key: 'history'
    })
    this.setData({
      history: []
    })
  },
  dwHandle() {
    wx.navigateTo({
      url: `../weather/weather`
    })
  },
  hotHandle(e) {
    const {
      value
    } = e.currentTarget.dataset
    console.log("hotHandle=", value);
    this.setLocal(value);
    const data = {
      "address": value
    }
    Request({
      url: Type.AddressGeocoder,
      data,
      success: (res) => {
        console.log("AddressGeocoder=", res)
        if (res.data.code == "200") {
          const {
            location: {
              lat,
              lng
            }
          } = res.data.result;
          wx.navigateTo({
            url: `../weather/weather?lat=${lat}&lng=${lng}&address=${value}`
          })
        } else {
          console.log("AddressGeocoder异常=", res)
        }

      }
    })


  },
  // 设置历史记录
  setLocal(val) {
    const arr = this.getLocal()
    if (!arr.includes(val)) {
      console.log(arr)
      arr.push(val)
      wx.setStorageSync('history', arr)
    }
  },
  getLocal() {
    return wx.getStorageSync('history') ? wx.getStorageSync('history') : []
  },
  delHandle(e) {
    const {
      value
    } = e.currentTarget.dataset
    const arr = this.getLocal()
    if (arr.includes(value)) {
      const index = arr.indexOf(value)
      arr.splice(index, 1)
      wx.setStorageSync('history', arr)
      this.setData({
        history: arr
      })
    }
  }
})