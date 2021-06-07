/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pendulumsimulator.math;

import pendulumsimulator.math.mat.Mat3f;
import pendulumsimulator.math.mat.Mat4f;
import pendulumsimulator.math.transf.TransfMat;
import pendulumsimulator.math.vec.Vec3f;

/**
 *
 * @author ANTONIO
 */
public class Cam {

    public Vec3f loc;
    public Vec3f rot;

    public float distToTarget;
    public float fov;

    public Cam(Vec3f loc, Vec3f tar, float fov) {
        this.loc = loc;
        this.rot = dirToRot_(tar.sub_(loc));
        this.distToTarget = tar.sub_(loc).norm();
        this.fov = fov;
    }

    public Mat3f getLocalAxis3f() {
        return TransfMat.eulerDegToMat_(rot);
    }

    public static void rotToDir(Vec3f rot, Vec3f dir) {
        dir.mul(TransfMat.eulerDegToMat_(rot));
    }

    public static Vec3f rotToDir_(Vec3f angles) {
        Vec3f dir = new Vec3f(0.0f, 0.0f, -1.0f);
        rotToDir(angles, dir);
        return dir;
    }

    public static void dirToRot(Vec3f dir, Vec3f rot) {
        rot.set(dir.anglesXZDeg_());
        rot.add(new Vec3f(90.0f, 0.0f, -90.0f));
    }

    public static Vec3f dirToRot_(Vec3f dir) {
        Vec3f rot = new Vec3f();
        dirToRot(dir, rot);
        return rot;
    }
    
    public Vec3f getDir() {
        return rotToDir_(rot);
    }

    public static void locRotToTarget(Vec3f loc, Vec3f rot, float distToTarget, Vec3f tar) {
        Vec3f dir = Cam.rotToDir_(rot);
        tar.set(dir.scale(distToTarget).add(loc));
    }

    public static Vec3f locRotToTarget_(Vec3f loc, Vec3f rot, float distToTarget) {
        Vec3f tar = new Vec3f();
        locRotToTarget(loc, rot, distToTarget, tar);
        return tar;
    }

    public Vec3f getTar() {
        return locRotToTarget_(loc, rot, distToTarget);
    }

    public void orbit(Vec3f dRot) {
        //Get target position before rotation
        Vec3f tar = getTar();

        //Rotate angles
        rot.add(dRot);

        //Rotate camera position around target
        loc = tar.add(getDir().negate().scale(distToTarget));
    }
    
    public void moveTowardsTarget(float distToTarget) {
        //Get target position before rotation
        Vec3f tar = getTar();

        //Move camera location
        loc = tar.add(getDir().negate().scale(distToTarget));

        //Set new distance to target
        this.distToTarget = distToTarget;
    }
    
    public Mat4f getModelViewMatrix() {
        Mat4f rx = TransfMat.rotationDeg_(-rot.x, new Vec3f(1.0f, 0.0f, 0.0f));
        Mat4f ry = TransfMat.rotationDeg_(-rot.y, new Vec3f(0.0f, 1.0f, 0.0f));
        Mat4f rz = TransfMat.rotationDeg_(-rot.z, new Vec3f(0.0f, 0.0f, 1.0f));
        Mat4f t = TransfMat.translation_(loc.negate_());
        return rx.mul_(ry).mul(rz).mul(t);
    }
}
