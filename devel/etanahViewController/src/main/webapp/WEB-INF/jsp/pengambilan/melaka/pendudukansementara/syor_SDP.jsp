<%-- 
    Document   : pemohonview
    Created on : Sep 2, 2010, 12:59:41 PM
    Author     : @mr5rule
--%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ include file="/WEB-INF/jsp/include/taglibs.jspf" %>
<style type="text/css">
    textarea {height: 200px; width: 500px;}
</style>
<script type="text/javascript">
    function save1(event, f){
    <%--alert(f);--%>
                 var q = $(f).formSerialize();
                 var url = f.action + '?' + event;
                 $.post(url,q,
                 function(data){
                     $('#page_div',self.document).html(data);
                 },'html');
            
             }

             function test(f) {
                 $(f).clearForm();
             }

             function RunProgram(strUserID, strNama, strJawatan, strIDPermohonan, strStageID) {

                 var strNama2 = ReplaceAll(strNama," ","_");
                 var strJawatan2 = ReplaceAll(strJawatan," ","_");
                 var strStageID2 = "g_kemaskini_charting";
                 var objShell = new ActiveXObject("WScript.Shell");
                 var sysVar = objShell.Environment("System");
                 //objShell.run("C:\Program Files\EpiWeb\ArcMap_ActiveX_Initializer\InititializeArcMap.exe");
                 //objShell.run("C:\Program Files\eTanahStrata\InititializeStrata.exe")


                 objShell.Run(sysVar("eTanahGISLIM") + " " + strUserID + " " + strNama2 + " " +  strJawatan2 + " " + strIDPermohonan + " " + strStageID2);
             }
     
</script>

<s:form beanclass="etanah.view.stripes.pengambilan.JabatanTeknikal2ActionBean">
    <s:messages />
    <s:errors />
    <div class="subtitle">
        <fieldset class="aras1">

            <legend>Syor <c:if test="${actionBean.kNegeri eq '04'}">Tolak</c:if><c:if test="${actionBean.kNegeri eq '05'}">SDP</c:if></legend>


            <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PLPS' and actionBean.permohonan.kodUrusan.kod ne 'PBMT'}">
                <p>
                    <label>Syor <c:if test="${actionBean.kNegeri eq '04'}">Tolak</c:if><c:if test="${actionBean.kNegeri eq '05'}">SDP</c:if> :</label>
                    <s:textarea name="syor"><%--value="${permohonanLaporPelan.syor}"--%></s:textarea>
                    </p>
                    <p>
                        <label>&nbsp;</label>
                    <s:button name="simpanSyorSDP" value="Simpan" class="btn" onclick="save1(this.name, this.form);" />
                    <s:button  name="reset" id="padam" value="Padam" class="btn" onclick="return test();"/>

                    <c:if test="${actionBean.stageId eq 'g_kemaskini_charting'}">
                        <s:button name="charting" value="Charting" class="btn" onclick="RunProgram('${actionBean.peng.idPengguna}','${actionBean.peng.nama}','${actionBean.peng.jawatan}','${actionBean.idPermohonan}','${actionBean.stageId}');"/>&nbsp;
                    </c:if>
                </p>
            </c:if>
            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PLPS' or actionBean.permohonan.kodUrusan.kod eq 'PBMT'}">
                <c:if test="${!actionBean.viewOnly}">
                    <p>
                        <label>Syor <c:if test="${actionBean.kNegeri eq '04'}">Tolak</c:if><c:if test="${actionBean.kNegeri eq '05'}">SDP</c:if> :</label>
                        <s:textarea name="syor" class="normal_text"><%--value="${permohonanLaporPelan.syor}"--%></s:textarea>
                        </p>                        
                        <p>
                            <label>&nbsp;</label>
                        <s:button name="simpanSyorSDP" value="Simpan" class="btn" onclick="save1(this.name, this.form);" />
                        <s:button  name="reset" id="padam" value="Padam" class="btn" onclick="return test();"/>

                        <c:if test="${actionBean.stageId eq 'g_kemaskini_charting'}">
                            <s:button name="charting" value="Charting" class="btn" onclick="RunProgram('${actionBean.peng.idPengguna}','${actionBean.peng.nama}','${actionBean.peng.jawatan}','${actionBean.idPermohonan}','${actionBean.stageId}');"/>&nbsp;
                        </c:if>
                    </p>
                </c:if>
                <c:if test="${actionBean.viewOnly}">
                    <p>
                        <label>Syor <c:if test="${actionBean.kNegeri eq '04'}">Tolak</c:if><c:if test="${actionBean.kNegeri eq '05'}">SDP</c:if> : </label>
                        <table width="70%" border="0">
                            <tr>
                                <td>
                                ${actionBean.syor}
                            </td>
                        </tr>
                    </table>    

                    </p>

                </c:if>
            </c:if>
            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PRIZ'}">
                <c:if test="${actionBean.stageId eq 'g_charting_kemaskini'}">
                    <s:button name="charting" value="Charting" class="btn" onclick="RunProgram('${actionBean.peng.idPengguna}','${actionBean.peng.nama}','${actionBean.peng.jawatan}','${actionBean.idPermohonan}','${actionBean.stageId}');"/>&nbsp;
                </c:if>
            </c:if>

        </fieldset>
    </div>           
</s:form>

