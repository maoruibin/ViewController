<?xml version="1.0"?>
<recipe>
	
    <instantiate from="root/src/app_package/ViewController.java.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${viewControllerClass}.java" />

    <open file="${escapeXmlAttribute(srcOut)}/${viewControllerClass}.java" />
</recipe>
