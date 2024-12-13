<%-- 
    Document   : kemaskini_permohonan_terdahulu
    Created on : Jan 23, 2015, 5:39:46 PM
    Author     : Hazwan
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Tambah Permohonan Terdahulu</title>
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
    var size = 0 ;
    function save(event, f){
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url,q,
        function(data){
            $('#page_div',opener.document).html(data);
            self.close();
        },'html');
    }
    
    function save(){
        self.opener.refreshPageTanahRizab();
        self.close();
    }
         
    function kemaskini(id,idm){
        //alert(id);
        //alert(idm);
       var url = '${pageContext.request.contextPath}/pembangunan/laporan_pelukisPelan?simpanFail&idPermohonan='+id+' &idMohonManual='+idm;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }
    
       
        
        
        
        
</script>
<body>

    <s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
    <s:form beanclass="etanah.view.stripes.pembangunan.LaporanPelukisPelanActionBean" name="form">
        <s:errors/>
        <s:messages/>

        <s:hidden name="idPermohonan" id="idPermohonan" value="${actionBean.idPermohonan}"/>'
        <s:hidden name="idMohonManual" id="idMohonManual" value="${actionBean.idMohonManual}"/>
       
        
            <div class="subtitle">
                <fieldset class="aras1">
                    <legend>Permohonan Terdahulu</legend>
                    <div class="content" align="center">
                        <table class="tablecloth" border="0">
                            <tr>
                                <td>No. Fail :</td>
                                <td><s:text name="noFail" id="noFail"/></td>
                            </tr>
                            <tr>
                                <td align="right" colspan="2">
                                    <s:button name="simpan" value="Simpan" class="btn" onclick="kemaskini('${actionBean.idPermohonan}','${actionBean.idMohonManual}')"/>
                                    <s:reset name="IsiSemula" value="Isi Semula" class="btn" onclick=""/>
                                    <s:button name="tutup" value="Kembali" class="btn" onclick="save()"/>
                                </td>
                            </tr>
                        </table>
                    </div>
                    <br/>

                </fieldset>
            </div>
</s:form>
</body>

