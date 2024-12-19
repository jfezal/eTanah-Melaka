<%--
    Document   :  laporan_tanahV2AddEditLotSmpdn.jsp
    Created on :  March 05, 2012, 11:28:13 AM
    Author     :  Shazwan
--%>




<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Carian Syarat Nyata</title>
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
    function save(event, f){
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url,q,
        function(data){
            $('#page_div',opener.document).html(data);
            self.close();
        },'html');
    }
    function uppercase(){
    <%-- alert("test");--%>
            var syaratNyata2 = document.getElementById("syaratNyata2").value;
            document.getElementById("syaratNyata2").value = syaratNyata2.toUpperCase();
        }

        function selectRadio(obj){

            document.getElementById("kod").value=obj.id;
            document.getElementById("syaratNyata").value=obj.value;

        }
        
        $(document).ready(function() {
            $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'})
        });
        
        function refreshpage(){
            NoPrompt();
            opener.refreshV2('main');
            self.close();
        }
        
    function selectCheckBox()
    {
        NoPrompt();
        var e= $('#sizeKod').val();
        var cnt=0;
        var data = new Array() ;
        for(cnt=0;cnt<e;cnt++)
        {
            if(e=='1'){
                if(document.frm.checkmate.checked) {
                    //                     alert(document.frm.checkmate[cnt].value) ;
                    data[cnt] = document.frm.checkmate.value ;
                }
                
            }
            else {
                if(document.frm.checkmate[cnt].checked) {
                    //                     alert(document.frm.checkmate[cnt].value) ;
                    data[cnt] = document.frm.checkmate[cnt].value ;
                }
                else{
                    data[cnt] = "T" ;
                }
            }
        }
        //                    alert(data) ;
        if(confirm("Adakah anda pasti?")) {
            var url = '${pageContext.request.contextPath}/pelupusan/laporan_tanahV2?simpanAgensi&item='+data ;
            $.get(url,
            function(data){
                alert("Rekod telah berjaya di masukkan") ;
                self.openFrameForJtek('jTeknikal');
            },'html');
        }
    }
    
    function openFrameForJtek(type){
            var idHakmilik = $('#idHakmilik').val();
            window.open("${pageContext.request.contextPath}/pelupusan/laporan_tanahV2?openFrame&idHakmilik="
                +idHakmilik+"&type="+type, "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=1000,height=500,scrollbars=yes");
            //        "status=0,toolbar=0,location=0,menubar=0,width=2000,height=900,scrollbars=yes,fullscreen=yes");
        }
</script>
<body>
    <script>
        // Allow the user to be warned by default.
        var allowPrompt = true;
                
        //window.onbeforeunload = WarnUser;
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
            allowPrompt = true;
        }

    </script>
    <s:errors/>
    <s:messages/>
    <div class="subtitle">
        <s:form beanclass="etanah.view.stripes.pelupusan.LaporanTanahV2PelupusanActionBean" name="frm">
            <fieldset class="aras1">
                <legend>
                    Carian Jabatan - Jabatan Teknikal
                </legend>
                <s:hidden id="selectedKod" name="selectedKod" />
                <s:hidden id="selectedNama" name="selectedNama" />
                <s:hidden id="selectedAgensi" name="selectedAgensi"/>
                <s:hidden name="idHakmilik" id="idHakmilik"/>
                <p>
                    <s:hidden name="index" id="index" />
                </p>
                <p>
                    <label>Kod :</label>
                    <s:text name="kod" id="kod"/>
                </p>
                <p>
                    <label>Nama Jabatan :</label>
                    <s:text name="kodAgensiNama" id="kodAgensiNama"  onkeyup="javascript:uppercase();"/>
                </p>

                <p>
                    <label>&nbsp;</label>
                    <s:submit name="cariKodAgensiJTK" value="Cari" class="btn" onclick="NoPrompt();"/>
                    <s:button name="tutup" value="Kembali" class="btn" onclick="openFrameForJtek('jTeknikal')"/>
                </p>
            </fieldset>
        </div>
        <br>

        <div class="subtitle">

            <fieldset class="aras1">
                <s:hidden name="${actionBean.sizeKod}" id="sizeKod" value="${actionBean.sizeKod}"/>
                <p>
                    <display:table style="width:100%" class="tablecloth" name="${actionBean.listKodAgensi}" pagesize="100" cellpadding="0" cellspacing="0" requestURI="/pelupusan/sedia_jabatan" id="line">
                        <display:column> <s:checkbox name="checkmate" id="checkmate" value="${line.kod}"/></display:column>
                        <display:column title="Kod" property="kod"/>
                        <display:column title="Nama Jabatan Teknikal" property="nama" style="text-transform:uppercase;"/>
                    </display:table>
                </p>
                <c:if test="${fn:length(actionBean.listKodAgensi) > 0}" >
                    <p><label>&nbsp;</label>
                        <s:button name="simpanAgensi" value="Simpan" class="btn" onclick="javascript:selectCheckBox();"/>
                    </p>
                </c:if>
            </fieldset>

        </s:form>
    </div>
</body>


