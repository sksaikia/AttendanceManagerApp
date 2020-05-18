const express = require('express');
const router = express.Router({mergeParams: true});
const helper = require('./../helper/auth');

router.post(`/signup`, helper.signup);
router.post(`/login`,helper.login)

module.exports = router;
