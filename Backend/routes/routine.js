const express = require('express');
const router = express.Router({mergeParams: true});
const helper = require('./../helper/routine')
const checkAuth = require('./../middleware/check_auth');

router.post(`/addRoutine`,checkAuth,helper.addRoutine)
router.get(`/getRoutine`,checkAuth,helper.getRoutines)
router.get(`/getRoutine/:id`,checkAuth,helper.getRoutine)
router.patch(`/getRoutine/:id`,checkAuth,helper.updateRoutine)
router.delete(`/getRoutine/:id`,checkAuth,helper.deleteRoutine)


module.exports = router;