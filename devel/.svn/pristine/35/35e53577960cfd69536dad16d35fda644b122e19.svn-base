<%-- 
    Document   : pelan_lokasi_GIS
    Created on : Jul 19, 2011, 6:06:05 PM
    Author     : zadhirul.farihim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<script type="text/javascript">
    
    // invoke this method if Kod_Urusan is equal to P8, P40A 
    function RunProgram(strUserID, strJawatan, strIDPermohonan, strIDStage) {
        // alert(strIDPermohonan);
        //alert(strIDStage);
        //strIDStage = 'g_strata_carian_lokasi'; //stageID on GIS module
        var objShell = new ActiveXObject("WScript.Shell");
        var sysVar = objShell.Environment("System");
        //objShell.run("C:\Program Files\EpiWeb\ArcMap_ActiveX_Initializer\InititializeArcMap.exe");
        //objShell.run("C:\Program Files\eTanahStrata\InititializeStrata.exe")        
        objShell.Run(sysVar("eTanahStrataEngine") + " " + strUserID + " " + strJawatan + " " + strIDPermohonan + " " + strIDStage);        
    }
    
    // invoke this method if Kod_Urusan not equal to P8, P40A 
    function RunProgram2(strUserID, strJawatan, strIDPermohonan, strIDStage) {
        strIDStage = 'g_strata_sedialaporan'; //stageID on GIS module

        var objShell = new ActiveXObject("WScript.Shell");
        var sysVar = objShell.Environment("System");
        //objShell.run("C:\Program Files\EpiWeb\ArcMap_ActiveX_Initializer\InititializeArcMap.exe");
        //objShell.run("C:\Program Files\eTanahStrata\InititializeStrata.exe")
        objShell.Run(sysVar("eTanahStrataEngine") + " " + strUserID + " " + strJawatan + " " + strIDPermohonan + " " + strIDStage);
    }
    
    function RunProgram1(strUserID, strJawatan, strIDPermohonan, strIDStage,event, f) {
        //  alert(strUserID);
        //  alert(strJawatan);
        //  alert(strIDPermohonan);
        //  alert(strIDStage);
        
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
            //objShell.run("C:\Program Files\EpiWeb\ArcMap_ActiveX_Initializer\InititializeArcMap.exe");
            //objShell.run("C:\Program Files\eTanahStrata\InititializeStrata.exe")
            objShell.Run(sysVar("eTanahStrataGIS") + " " + strUserID + " " + strJawatan + " " + strIDPermohonan + " " + strIDStage);   
        }catch(ex){
            alert(ex);
        }
    }
    
</script>
<s:form name="form1" beanclass="etanah.view.strata.GisStrataActionBean">
    <s:messages/>
    <s:errors/>
    <div>
        <fieldset class="aras1">
            <legend>Paparan Pelan</legend>
            
                <c:if test="${!actionBean.isDokumenDeleted}">
                    <p><font size="1" color="Red">*</font>Sila klik untuk melihat Pelan Lokasi.</p>   
                    </c:if>

                
            <p>&nbsp;</p>
            <%--
            <!--            if other urusan is using this screen, cater by kod_urusan -->
            <c:if test="${KuatkuasaStrata}">
                <p>
                    <s:button name="pelanLokasi" id="btnClick" value="Pelan Lokasi" class="longbtn" onclick="RunProgram2('ptspoc1','Pembantu_Tadbir_SPOC','${actionBean.idPermohonan}','${actionBean.stageId}')" />
<!--                    <s:button name="simpanPelanLokasi" id="btnClick" value="Simpan Pelan" class="longbtn" onclick="doSubmit(this.form,this.name,'page_div')" />a-->
                </p>
            </c:if>
            <c:if test="${!KuatkuasaStrata}">
                <p>
                    <s:button name="pelanLokasi" id="btnClick" value="Pelan Lokasi" class="longbtn" onclick="RunProgram('ptspoc1','Pembantu_Tadbir_SPOC','${actionBean.idPermohonan}','${actionBean.stageId}')" />
<!--                    <s:button name="simpanPelanLokasi" id="btnClick" value="Simpan Pelan" class="longbtn" onclick="doSubmit(this.form,this.name,'page_div')" />a-->
                </p>
            </c:if>
            --%>
            <p>  
                <%--
                <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'P8'}">
                <s:button name="transferFile" id="btnClick" value="Pelan Lokasi" class="btn" onclick="RunProgram('ptspoc1','Pembantu_Tadbir_SPOC','${actionBean.idPermohonan1}','${actionBean.stageId1}',this.name, this.form)" />
                </c:if>
                
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'P8'}">
                    <c:if test="${actionBean.integrasiPermohonan1 eq null}">
                    <s:button name="simpanPelanLokasi" id="btnClick" value="Simpan Pelan" class="longbtn" onclick="doSubmit(this.form,this.name,'page_div')" />    
                    </c:if>
                    <c:if test="${actionBean.integrasiPermohonan1 ne null}">
                <s:button name="transferFile" id="btnClick" value="Pelan Lokasi" class="btn" onclick="RunProgram('ptspoc1','Pembantu_Tadbir_SPOC','${actionBean.idPermohonan1}','${actionBean.stageId1}',this.name, this.form)" />
                </c:if>
                </c:if>
                --%>
                <c:choose>
                    <c:when test="${actionBean.isDokumenDeleted}">
                        <font size="2" color="Red">Sila klik Tab 'Dokumen' untuk memuatnaik fail XML Pelan Lokasi Strata (PLKTA). </font>
                    </c:when>
                    <c:otherwise>
                        <s:button name="transferFile" id="btnClick" value="Pelan Lokasi" class="btn" onclick="RunProgram1('ptspoc1','Pembantu_Tadbir_SPOC','${actionBean.idPermohonan1}','${actionBean.stageId1}',this.name, this.form)" />
                    </c:otherwise>
                </c:choose>                
            </p>

        </fieldset>
    </div>

</s:form>