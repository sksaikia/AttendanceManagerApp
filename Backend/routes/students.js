const express = require('express');
const router = express.Router({mergeParams: true});
const helper = require('./../helper/students')
const checkAuth = require('./../middleware/check_auth');

router.post(`/addStudent`,checkAuth,helper.addStudent)
router.get(`/getAllStudents`,checkAuth,helper.getAllStudents)
router.get(`/getStudent/:id`,checkAuth,helper.getStudent)
router.patch(`/getStudent/:id`,checkAuth,helper.updateStudent)
router.delete(`/getStudent/:id`,checkAuth,helper.deleteStudent)
 
module.exports = router;