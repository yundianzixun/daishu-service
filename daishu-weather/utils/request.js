const Request = function (option) {
  wx.request({
    header: {
      'content-type': 'application/json'
    },
    ...option,
    success(res) {
      option.success(res)
    }
  })
}

export default Request