const express = require('express');
const router = express.Router({mergeParams: true});
const helper = require('./../helper/teachers')
const checkAuth = require('./../middleware/check_auth');


router.post(`/addTeacher`,checkAuth,helper.addTeacher)
router.get(`/getAllTeachers`,checkAuth,helper.getAllTeachers)
router.get(`/getTeacher/:id`,checkAuth,helper.getTeacher)
router.patch(`/getTeacher/:id`,checkAuth,helper.updateTeacher)
router.delete(`/getTeacher/:id`,checkAuth,helper.deleteTeacher)


module.exports = router;