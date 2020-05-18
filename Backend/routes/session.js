const express = require('express');
const router = express.Router({mergeParams: true});
const helper = require('./../helper/session')
const checkAuth = require('./../middleware/check_auth');

router.post(`/addSession`,checkAuth,helper.addSession)
router.get(`/getSessions`,checkAuth,helper.getSessions)
router.get(`/getSession/:id`,checkAuth,helper.getSession)
router.patch(`/getSession/:id`,checkAuth,helper.updateSession)
router.delete(`/getSession/:id`,checkAuth,helper.deleteSession)

module.exports = router;