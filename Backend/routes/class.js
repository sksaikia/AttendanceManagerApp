const express = require('express');
const router = express.Router({mergeParams: true});
const helper = require('./../helper/class')
const checkAuth = require('./../middleware/check_auth');

router.post(`/addClass`,checkAuth,helper.addClass)
router.get(`/getClasses`,checkAuth,helper.getClasses)
router.get(`/getClass/:id`,checkAuth,helper.getClass)
router.patch(`/getClass/:id`,checkAuth,helper.updateClass)
router.delete(`/getClass/:id`,checkAuth,helper.deleteClass)

module.exports = router;