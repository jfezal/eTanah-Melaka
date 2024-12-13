<%--
    Document   :  laporan_tanahGSAKeadaanTanah.jsp
    Created on :  Jan 17, 2012, 10:50:13 AM
    Author     :  Shazwan
--%>



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>PERIHAL LOT-LOT BERSEMPADAN</title>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/ui.datepicker.js"></script>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/ui.core.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script src="<%= request.getContextPath()%>/pub/js/ui.datepicker-ms.js" type="text/javascript"></script>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/maxwindow.js"></script>
<script type="text/javascript">
    
    $(document).ready(function() {
         
        //            maximizeWindow();
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
        
    }); //END OF READY FUNCTION
         
        
    
    function validateNumber(elmnt,content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = removeNonNumeric(content);
            return;
        }
    }
    function openFrame(type){
        NoPrompt();
        var idHakmilik = $('#idHakmilik').val();
        //    alert(idHakmilik);
        window.open("${pageContext.request.contextPath}/pelupusan/laporan_tanahGSA?openFrame&idHakmilik="
            +idHakmilik+"&type="+type, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=1000,height=500,scrollbars=yes");
        //        "status=0,toolbar=0,location=0,menubar=0,width=2000,height=900,scrollbars=yes,fullscreen=yes");
    }
    function refreshpage(){
        NoPrompt();
        opener.refreshGSA('main');
        self.close();
    }    
</script>
<body>
    <script>
        // Allow the user to be warned by default.
        var allowPrompt = true; 
        window.onbeforeunload = WarnUser;
        function WarnUser()
        {   
            if(allowPrompt)
                refreshpage();
            if(allowPrompt)
            {
                event.returnValue = "Segala Maklumat tidak akan disimpan, anda pasti?";
            }
            else
            {
                // Reset the flag to its default value.
                allowPrompt = true;
            }
        }
        function NoPrompt()
        {
            allowPrompt = false;
        }

    </script>
    <s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
    <s:form beanclass="etanah.view.stripes.pelupusan.LaporanTanahGSAPelupusanActionBean" name="form">
        <s:errors/>
        <s:messages/>
        <s:hidden name="statusSempadan"/>
        <div class="subtitle" id="main">
            <fieldset class="aras1">
                <s:hidden name="idHakmilik" id="idHakmilik"/>
                <div id="perihaltanah">
                    <legend>Perihal Lot-Lot Bersempadan</legend>
                    <div class="content" align="center">
                        <table class="tablecloth" border="0">
                            <tr>
                                <td>Sempadan :</td>
                                <td>
                                    <c:if test="${empty actionBean.statusSempadan}">
                                        <s:select name="index" id="index" >
                                            <s:option value="U">Utara</s:option>
                                            <s:option value="S">Selatan</s:option>
                                            <s:option value="T">Timur</s:option>
                                            <s:option value="B">Barat</s:option>
                                            <%--<s:options-collection collection="${list.senaraiKegunaanTanah}" label="nama" value="kod"/>--%>
                                        </s:select>
                                    </c:if>
                                    <c:if test="${!empty actionBean.statusSempadan}">
                                        <s:select name="index" id="index" value="${actionBean.jenisSmpdn}">
                                            <s:option value="U">Utara</s:option>
                                            <s:option value="S">Selatan</s:option>
                                            <s:option value="T">Timur</s:option>
                                            <s:option value="B">Barat</s:option>
                                            <%--<s:options-collection collection="${list.senaraiKegunaanTanah}" label="nama" value="kod"/>--%>
                                        </s:select>
                                    </c:if>
                                </td>

                            </tr>
                            <tr>
                                <td>No. Hakmilik :</td>
                                <td>
                                    <c:if test="${empty actionBean.statusSempadan}">
                                        <s:text name="idHakmilikSmpdn"/>
                                    </c:if>
                                    <c:if test="${!empty actionBean.statusSempadan}">                            
                                        ${actionBean.idHakmilikSmpdn}
                                    </c:if>                        
                                </td>
                            </tr>
                            <tr>
                                <td>Kegunaan Tanah :</td>
                                <td>
                                    <c:if test="${empty actionBean.statusSempadan}">
                                        <s:textarea name="kegunaan" id="kegunaan" rows="5" cols="80"></s:textarea>
                                    </c:if>
                                    <c:if test="${!empty actionBean.statusSempadan}">
                                        <s:textarea name="kegunaan" id="kegunaan" rows="5" cols="80" value="${actionBean.kegunaanSmpdn}"></s:textarea>
                                    </c:if>                        
                                </td>
                            </tr>
                            <tr>
                                <td>Keadaan Tanah :</td>
                                <td>
                                    <c:if test="${empty actionBean.statusSempadan}">
                                        <s:textarea name="keadaanTanah" id="keadaanTanah" rows="5" cols="80"></s:textarea>
                                    </c:if>
                                    <c:if test="${!empty actionBean.statusSempadan}">
                                        <s:textarea name="keadaanTanah" id="keadaanTanah" rows="5" cols="80" value="${actionBean.keadaanTanah}"></s:textarea>
                                    </c:if>                        
                                </td>
                            </tr>
                            <tr>
                                <td>Catatan :</td>
                                <td>
                                    <c:if test="${empty actionBean.statusSempadan}">
                                        <s:textarea name="catatan" id="catatan" rows="5" cols="80"></s:textarea>
                                    </c:if>
                                    <c:if test="${!empty actionBean.statusSempadan}">
                                        <s:textarea name="catatan" id="catatan" rows="5" cols="80" value="${actionBean.catatan}"></s:textarea>
                                    </c:if>

                                </td>
                            </tr>
                            <tr>
                                <td>Milik :</td>
                                <td>
                                    <c:if test="${empty actionBean.statusSempadan}">
                                        <s:select name="milikKerajaan" id="milikKerajaan" >
                                            <s:option value="T">Milik</s:option>
                                            <s:option value="Y">Kerajaan</s:option>
                                            <%--<s:options-collection collection="${list.senaraiKegunaanTanah}" label="nama" value="kod"/>--%>
                                        </s:select>
                                    </c:if>
                                    <c:if test="${!empty actionBean.statusSempadan}">
                                        <s:select name="milikKerajaan" id="milikKerajaan" value="${actionBean.milikKerajaanSmpdn}">
                                            <s:option value="T">Milik</s:option>
                                            <s:option value="Y">Kerajaan</s:option>
                                            <%--<s:options-collection collection="${list.senaraiKegunaanTanah}" label="nama" value="kod"/>--%>
                                        </s:select>
                                    </c:if>

                                </td>
                            </tr>
                            <tr>
                                <td align="right" colspan="2">
                                    <%--<s:button name="simpanKandunganSempadan" id="save" value="Simpan" class="btn" onclick="saveSempadan(this.name, this.form);"/>--%>
                                    <c:if test="${empty actionBean.statusSempadan}">
                                        <s:submit name="simpanKandunganSempadan" value="Simpan" class="btn" onclick="NoPrompt();"/>
                                        <s:button name="showEditSempadan" value="Isi Semula" class="btn" onclick="resetUlasan(this.name, this.form);" />
                                    </c:if>
                                    <c:if test="${!empty actionBean.statusSempadan}">   
                                        <s:submit name="simpanKandunganSempadan" value="Kemaskini" class="btn" onclick="NoPrompt();"/>
                                    </c:if>
                                    <s:button name="tutup" value="Kembali" class="btn" onclick="openFrame('lSempadan')"/>
                                    <%--<s:button name="closeWindow" value="Tutup" class="btn" onclick="closeWindow123(this.name, this.form)" />--%> 
                                </td>
                            </tr>
                        </table>

                    </div>
                    <br/>
                </div>
            </fieldset>
        </div>
    </s:form>
</body>

