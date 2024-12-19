<%-- 
    Document   : gisStrata
    Author     : Murali
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<script type="text/javascript">
    function RunProgram(strUserID, strJawatan, strIDPermohonan, strIDStage,event, f) {       
        try{
            var q = $(f).formSerialize();
            var url = f.action + '?' + event;
            $.post(url,q,
            function(data){
                $('#page_div',opener.document).html(data);
                self.close();
            },'html');
            var objShell = new ActiveXObject("WScript.Shell");
            var sysVar = objShell.Environment("System");            
            objShell.Run(sysVar("eTanahStrataGIS") + " " + strUserID + " " + strJawatan + " " + strIDPermohonan + " " + strIDStage);   
        }catch(ex){
            alert(ex);
        }
    }
</script>
<s:form beanclass="etanah.view.strata.GisStrataMlkActionBean">
    <s:messages/>
    <s:errors/>
    <div>
        <fieldset class="aras1">
            <legend>Paparan Pelan</legend> <br />
            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'P8' || actionBean.permohonan.kodUrusan.kod eq 'P22A' 
                          || actionBean.permohonan.kodUrusan.kod eq 'P40A' || actionBean.permohonan.kodUrusan.kod eq 'RTHS'
                          || actionBean.permohonan.kodUrusan.kod eq 'RTPS' || actionBean.permohonan.kodUrusan.kod eq 'PPRUS'}">
                <c:if test="${actionBean.isDokumenUploaded}">
                    <p><font size="1" color="Red"> * </font>Sila klik Pelan Strata untuk melihat pelan.</p>
                    <br>
                    <p>&nbsp;</p>
                    <p> 
                        <s:button name="transferFile" id="btnClick" value="Pelan Strata" class="btn" onclick="RunProgram('ptspoc1','Pembantu_Tadbir_SPOC','${actionBean.idPermohonan}','${actionBean.stageId}',this.name, this.form)" />
                    </p>
                </c:if>
                <c:if test="${!actionBean.isDokumenUploaded}">
                    <p><font size="2" color="Red"> * Sila klik tab ' Dokumen ' untuk memuat naik fail xml Pelan Lokasi Strata (PLKTA).</font></p>
                </c:if>
            </c:if>

            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBBS' || actionBean.permohonan.kodUrusan.kod eq 'PBBD' 
                          || actionBean.permohonan.kodUrusan.kod eq 'PBS' || actionBean.permohonan.kodUrusan.kod eq 'PHPC'
                          || actionBean.permohonan.kodUrusan.kod eq 'PHPP' || actionBean.permohonan.kodUrusan.kod eq 'PSBS'}">


                  <c:if test="${actionBean.isDokumenUploaded}">
                      <p><font size="1" color="Red"> * </font>Sila klik Pelan Strata untuk melihat pelan.</p>
                      <br>
                      <p>&nbsp;</p>
                      <p> 
                          <s:button name="transferJppFile" id="btnClick" value="Pelan Strata" class="btn" onclick="RunProgram('ptspoc1','Pembantu_Tadbir_SPOC','${actionBean.idPermohonan}','${actionBean.stageId}',this.name, this.form)" />
                      </p>
                  </c:if>
                  <c:if test="${!manual}">
                      <c:if test="${!actionBean.isDokumenUploaded}">
                          <p><font size="2" color="Red"> * Sila klik tab ' Dokumen ' untuk memuat naik fail xml Jadual Petak (JPP).</font></p>
                      </c:if>
                  </c:if>
                  <c:if test="${manual}">
                      <p><font size="2" color="Red"> * Tab ini hanya berfungsi sekiranya menggunakan fail XML(Jadual Petak)</font></p>
                  </c:if>


            </c:if>


        </fieldset>
    </div>
</s:form>