<%--    
    Document   : speks
    Created on : Jul 22, 2010, 4:21:36 PM
    Author     : amir.muhaimin
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
            if(dl == null || dl == ''){
                $("#mt").attr("disabled", true);
            }
        });
        
        function regen(noPenyata,jenisFail,statusInfoId){
                

$('#noPenyataR').val(noPenyata);
$('#jenisFail').val(jenisFail);
$('#statusInfoId').val(statusInfoId);

    }
        
    function click_clear(){
        document.formSPEKS.datepicker.value="";
    }
    </script>
    <stripes:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
    <stripes:messages />
    <stripes:errors />
    <stripes:form beanclass="etanah.view.stripes.ispeks.SenaraiStatusInfoActionBean" id="formSPEKS" name="formSPEKS">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Fail SPEKS </legend>
               
                <br />
                <p>
                    <label>Tarikh :</label>
                    <stripes:text name="tarikh" id="datepicker" onblur="javascript:checking(this.form);" size="20;" class="datepicker" />
                </p>
                <p>
                    <label>Status :</label>
                    <stripes:select name="caraBayar">
                        <stripes:option value="0" label="Pilih ..." />
                                <stripes:options-collection collection="${listUtil.senaraiKodCaraBayaran}"  label="nama" value="kod" sort="nama"/>
                            </stripes:select>
                </p>
                             
                <br />
                <p>
                    <label>&nbsp;</label>
                    <stripes:submit name="populateResit" value="Papar" class="btn"/>
                    <!-- stripes:button name="reset" value="Isi Semula" class="btn" onclick="click_clear();"/ -->
                    <%--<stripes:submit id="mt" name="downloadFile" value="Muatturun" class="btn"/>--%>
                </p>
                <br />
            </fieldset>
                <fieldset class="aras1">
                <legend>Fail SPEKS </legend>
                <p class=instr>
<!--                    <em><font color="black">Sila masukkan Tarikh.</font></em>-->
                </p>
               <p>
                        <display:table style="width:100%" class="tablecloth" name="${actionBean.listStatusInfoIspeks}"  cellpadding="0" cellspacing="0" id="line">
                            <display:column title="Bil">${line_rowNum} <stripes:hidden name="idKewDok">${line.statusInfo.id}</stripes:hidden></display:column>
                            <display:column title="No Rujukan" property="statusInfo.noRef"/>
                            <display:column title="Peringkat" property="peringkat"/>
                            <display:column title="Status IDD" property="statusInfo.stsIDD"/>
                            <display:column title="Status Encrypt" property="statusInfo.stsEncryptDecrypt"/>
                            <display:column title="Status Transfer" property="statusInfo.stsTransfer"/>
                            <display:column title="Tarikh Terima" property="statusInfo.infoAudit.tarikhMasuk"/>
                            <display:column title="Tindakan">
                                <c:if test="${line.ppResend ne 'disable' && line.statusInfo.jenisFail eq 'PP'}">
                                    <stripes:submit name="hantarReJana" value="Hantar Jana" class="btn" onclick="regen('${line.statusInfo.noRef}','${line.statusInfo.jenisFail}','${line.statusInfo.id}')"/>
                                </c:if>
                                <c:if test="${line.statusInfo.jenisFail ne 'PP'}">
                                    <stripes:submit name="hantarReJana" value="Hantar Jana" class="btn" onclick="regen('${line.statusInfo.noRef}','${line.statusInfo.jenisFail}','${line.statusInfo.id}')"/>
                                </c:if>
                            </display:column>
                       
                        </display:table>
                    </p>
            </fieldset>
                    
        </div>
                    <stripes:hidden name="noPenyataR" id="noPenyataR"/>
                    <stripes:hidden name="jenisFail" id="jenisFail"/>
                                        <stripes:hidden name="statusInfoId" id="statusInfoId"/>

    </stripes:form>
</div>


    