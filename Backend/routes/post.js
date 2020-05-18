const express = require('express');
const router = express.Router({mergeParams: true});
const helper = require('./../helper/post');
const checkAuth = require('./../middleware/check_auth');

router.post('/post',checkAuth,helper.addPost)

module.exports = router;