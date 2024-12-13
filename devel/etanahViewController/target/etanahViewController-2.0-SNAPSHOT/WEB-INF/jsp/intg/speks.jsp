<%--    
    Document   : speks
    Created on : Jul 22, 2010, 4:21:36 PM
    Author     : amir.muhaimin
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<!DOCTYPE HTML PUBLIC "-//W3C//Dlabel HTML 4.01 pansitional//EN"
    "http://www.w3.org/p/html4/loose.dlabel">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<title>Fail SPEKS</title>
<div id="jab">
    <div align="center">
        <table style="width:99.2%" bgcolor="green">
            <tr><td width="100%" height="20" ><div style="background-color: green;" align="center">
                        <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">SPEKS</font>
                    </div></td></tr>
        </table><br>
    </div><br>
    <script type="text/javascript">
        $(document).ready( function(){
            var dl =   '${actionBean.downloadLink}';
            if(dl == null || dl == ''){
                $("#mt").attr("disabled", true);
            }
        });
        
    function click_clear(){
        document.formSPEKS.datepicker.value="";
    }
    </script>
    <stripes:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
    <stripes:messages />
    <stripes:errors />
    <stripes:form beanclass="etanah.view.stripes.intg.GenerateSPEKSActionBean" id="formSPEKS" name="formSPEKS">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Fail SPEKS </legend>
                <p class=instr>
                    <em><font color="black">Sila masukkan Tarikh.</font></em>
                </p>
                <br />
                <p>
                    <label>Tarikh :</label>
                    <stripes:text name="tarikhMasuk" id="datepicker" onblur="javascript:checking(this.form);" size="20;" class="datepicker" />
                </p>
                <br />
                <p>
                    <label>&nbsp;</label>
                    <stripes:submit name="generateFiles" value="Jana" class="btn"/>
                    <!-- stripes:button name="reset" value="Isi Semula" class="btn" onclick="click_clear();"/ -->
                    <stripes:hidden name="downloadLink" value="${actionBean.downloadLink}" />
                    <stripes:submit id="mt" name="downloadFile" value="Muatturun" class="btn"/>
                </p>
                <br />
            </fieldset>
        </div>
    </stripes:form>
</div>


    