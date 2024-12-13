<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Carian Kod Syarat Nyata</title>
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
    function validation(value){
        if(value == null){
            return true ;
        }
        return false ;
    }

    function trying(b){
        //            alert(b);
        document.salinan.data.value = b;
    }
        
    function tambah(radio) {
        if(validation(radio)){
            alert("Sila pilih kod agensi");
            return false ;
        }
        else {
            //            alert(radio) ;
            var kod = $('#kodAsal').val() ;
            var idRujukan = $('#idRujukan').val() ;
            var url = '${pageContext.request.contextPath}/pengambilan/sedia_jabatan?simpanKodSalinan&kod=' + kod + '&radio=' + radio + '&idRujukan=' + idRujukan ;
            $.get(url,
            function(data){
                $('#page_div').html(data);
                alert("Rekod telah berjaya di masukkan") ;
                self.search1(kod,idRujukan) ;
                //                        self.close();
                        
                       
            },'html');
        }            
    }
        
    function search1(kod,idRujukan){
        // alert("search:"+index);
        var url = '${pageContext.request.contextPath}/pengambilan/sedia_jabatan?edit&kod='+kod+'&idRujukan='+ idRujukan;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=910,height=800,scrollbars=yes");
    }

    function test(){
        var index=document.getElementById('index').value;
        opener.document.getElementById('kod'+index).value = $("#selectedKod").val();
        opener.document.getElementById('namaJabatan'+index).value = $("#selectedNama").val();
        self.close();
    }

    function closeWindow() {
        //uncomment to open a new window and close this parent window without warning
        //var newwin=window.open("popUp.htm",'popup','');
        if(navigator.appName=="Microsoft Internet Explorer") {
            this.focus();self.close(); }
        else { window.open('','eTanah',''); window.close(); }
    }

    function uppercase(){
    <%-- alert("test");--%>
            var kodAgensiNama = document.getElementById("kodAgensiNama").value;
            document.getElementById("kodAgensiNama").value = kodAgensiNama.toUpperCase();
        }

        function selectRadio(obj){
            document.getElementById("selectedKod").value=obj.id;
            document.getElementById("selectedNama").value=obj.value;
        }

        $(document).ready(function() {
            maximizeWindow();
            $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
        });

        function testingKod(v) {
            //alert($('radio').val) ;
            document.getElementById('IDkodMohon_ruj_luar').value=v;
            //alert(document.getElementById('IDkodMohon_ruj_luar').value);
        }

</script>
<s:errors/>
<s:messages/>
<div class="subtitle">
    <s:form beanclass="etanah.view.stripes.pengambilan.JabatanTeknikalPengambilanActionBean" name="salinan">
        <%-- <s:hidden name="idHakmilik" />--%>
        <s:hidden name="kodMohon_ruj_luar" id="IDkodMohon_ruj_luar" />
        <fieldset class="aras1">
            <legend>
                Kod Agensi
            </legend>
            <s:hidden id="selectedKod" name="selectedKod" />
            <s:hidden id="selectedNama" name="selectedNama" value="" />
            <s:hidden id="index" name="index"/>
            <s:hidden id="kodAsal" name="kodAsal"/>
            <s:hidden id="idRujukan" name="idRujukan"/>
            <p>
                <label>Kod :</label>
                <s:text name="kod" id="kod"/>
            </p>
            <p>
                <label>Nama Jabatan :</label>
                <s:text name="kodAgensiNama" id="kodAgensiNama"  onkeyup="javascript:uppercase();"/>
            </p>
            <p><label>&nbsp;</label>
                <s:submit name="cariKodAgensi2" value="Cari" class="btn"/>
                <%--<s:button name="tutup" value="Tutup" class="btn" onclick="javascript:window.close();"/>--%>
                <s:button name="showForm" id="kembali" value="Kembali" class="btn" onclick="javascript:search1(document.getElementById('kodAsal').value,document.getElementById('idRujukan').value)"/>
            </p>
        </fieldset>
    </div>
    <br>

    <div class="subtitle" align="center">
        <%-- <c:if test="${actionBean.kodSyaratNyata != null}">--%>
        <s:hidden name="data" id="data"/>
        <fieldset class="aras1">
            <legend></legend>
            <p>
                <display:table style="width:100%" class="tablecloth" name="${actionBean.listKodAgensi}" pagesize="15" cellpadding="0" cellspacing="0" requestURI="/pelupusan/sedia_jabatan" id="line">
                    <display:column> <s:radio name="radio" id="radio" value="${line.kod}" onclick="javascript:trying(this.value);"/></display:column>
                    <display:column title="Kod" property="kod"/>
                    <display:column title="Nama Kod Agensi" property="nama" style="text-transform:uppercase;"/>
                    <display:column title="Kategori Agensi" property="kategoriAgensi.nama" style="text-transform:uppercase;"/>
                </display:table>
            </p>

            <c:if test="${fn:length(actionBean.listKodAgensi) > 0}">
                <p>
                    <s:button name="simpanKodSalinan" value="Simpan" id="simpanKodSalinan" class="btn" onclick="javascript:tambah(document.getElementById('data').value);" />
                </p>
            </c:if>        
        </fieldset>
    </div>
</s:form>
