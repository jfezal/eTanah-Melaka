<%-- 
    Document   : tambah_agensi_kerajaan
    Created on : Sep 21, 2012, 12:03:35 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Carian Agensi</title>
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
        maximizeWindow();
    })
    function selectRadioBox()
    {
        //                alert("test");
        var e= $('#sizeKod').val();
        //                alert(document.frm.radiomate.value);
        var cnt=0;
        var data = new Array() ;
                
        for(cnt=0;cnt<e;cnt++)
        {
            //                     alert("test1
            if(e=='1'){
                if(document.frm.radiomate.checked) {
                    //                     alert(document.frm.checkmate[cnt].value) ;
                    data[cnt] = document.frm.radiomate.value ;
                    //                     alert(data[cnt])
                }
            }
            else{
                if(document.frm.radiomate[cnt].checked) {
                    //                     alert(document.frm.checkmate[cnt].value) ;
                    data[cnt] = document.frm.radiomate[cnt].value ;
                    //                     alert(data[cnt])
                }
                else{
                    data[cnt] = "T" ;
                }
            }
                     
        }
        if(confirm("Adakah anda pasti?")) {
            var url = '${pageContext.request.contextPath}/pelupusan/maklumat_agensi_kerajaan?simpanAgensi&item='+data ;
            $.get(url,
            function(data){
                $('#page_div').html(data);
                alert("Rekod telah berjaya di masukkan") ;
                self.close() ;
                self.opener.refreshMaklumatPemohon() ;
            },'html');
        }
    }
</script>

<s:errors/>
<s:messages/>
<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<div class="subtitle">
    <s:form beanclass="etanah.view.stripes.pelupusan.MaklumatAgensiKerajaanActionBean" name="frm">
        <fieldset class="aras1">
            <legend>
                Kod Agensi
            </legend>
            <p>
                <label>Nama Agensi :</label>
                <s:text name="kodAgensiNama" id="kodAgensiNama"  onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label><font color="red">*</font>Negeri :</label>
                <s:select name="negeri" id="negeri" >
                    <s:option value="">Pilih</s:option>
                    <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod" />
                </s:select>
            </p>
            <p><label>&nbsp;</label>
                <s:submit name="cariKodAgensi" value="Cari" class="btn"/>
                <s:button name="tutup" value="Tutup" class="btn" onclick="javascript:window.close();"/>
            </p>
        </fieldset>
        <div class="subtitle">
            <fieldset class="aras1">
                <div id="jtk1">
                    <s:hidden name="${actionBean.sizeKod}" id="sizeKod" value="${actionBean.sizeKod}"/>
                    <p>
                        <display:table style="width:100%" class="tablecloth" name="${actionBean.listKodAgensi}" pagesize="100" cellpadding="0" cellspacing="0" requestURI="/pelupusan/sedia_jabatan" id="line">
                            <display:column> <s:radio name="radiomate" id="radiomate" value="${line.kod}"/></display:column>
                            <display:column title="Nama Agensi" property="nama" style="text-transform:uppercase;"/>
                            <%--      <display:column title="Kategori Agensi" property="kategoriAgensi.nama" style="text-transform:uppercase;"/> --%>
                        </display:table>
                    </p>
                    <c:if test="${fn:length(actionBean.listKodAgensi) > 0}" >
                        <c:if test="${agensi eq true}">
                            <p><label>&nbsp;</label>
                                <s:button name="simpanAgensi" value="Simpan" class="btn" onclick="javascript:selectRadioBox();"/>
                            </p>
                        </c:if>
                    </c:if>
                </div>
            </fieldset>
        </div>
    </s:form>
</div>