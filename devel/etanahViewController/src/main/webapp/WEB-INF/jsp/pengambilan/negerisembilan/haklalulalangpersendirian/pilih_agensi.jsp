<%--
    Document   :  pilih_agensi
    Created on :  Oct 12, 2011, 4:44:13 PM
    Author     :  Murali
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

    function test(){
        var index=document.getElementById('index').value;
        opener.document.getElementById('kod'+index).value = $("#selectedKod").val();
        opener.document.getElementById('namaJabatan'+index).value = $("#selectedNama").val();
        self.close();
    }

    function test2(){
        var index=document.getElementById('index').value;
        opener.document.getElementById('salinanKod'+index).value = $("#selectedKod").val();
        opener.document.getElementById('salinanKepada'+index).value = $("#selectedNama").val();
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
                
        $(document).ready(function() {
            maximizeWindow();
            if(document.getElementById('selectedAgensi').value == 'JTK'){
                $('#jtk').show() ;
                $('#jtk1').show() ;
                $('#adn').hide() ;
                $('#adn1').hide() ;
            }
            else if (document.getElementById('selectedAgensi').value == 'ADN'){
                $('#adn').show() ;
                $('#adn1').show() ;
                $('#jtk').hide() ;
                $('#jtk1').hide() ;
            }
            else {
                $('#jtk').hide() ;
                $('#adn').hide() ;
                $('#jtk1').hide() ;
                $('#adn1').hide() ;
            }
            $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});                   
    
        });
        function selectCheck(a){
//            alert("value:"+ a) ;
            size++ ;
//            alert("size:" + size) ;
                                    
            if(a != null){
                for(var z = 0;i<size;i++){
                    array[z + i] = a ;
                }
            }
                       
            i++ ;
//            alert("arraySize:" + i) ;
            data = document.test.array ;
        }
                
        function selectCheckBox()
        {
            var e= $('#sizeKod').val();
            var cnt=0;
            var data = new Array() ;
            for(cnt=0;cnt<e;cnt++)
            {
                if(e=='1'){
                    if(document.frm.checkmate.checked) {
                        //                     alert(document.frm.checkmate[cnt].value) ;
//                        alert("1");
                        data[cnt] = document.frm.checkmate.value ;
                    }
                }
                else {
//                    alert("2");
                    if(document.frm.checkmate[cnt].checked) {
                        //                     alert(document.frm.checkmate[cnt].value) ;
//                         alert("2.1");
                        data[cnt] = document.frm.checkmate[cnt].value ;
                    }
                    else{
//                        alert("2.2");
                        data[cnt] = "T" ;
                    }
                }
            }
            //                    alert(data) ;
            var idHakmilik= '${actionBean.idHakmilik}';
            <%--alert(idHakmilik);--%>
            if(confirm("Adakah anda pasti?")) {
                var url = '${pageContext.request.contextPath}/pengambilan/sedia_jabatan?simpanAgensi&item='+data+"&idHakmilik="+idHakmilik;
                $.get(url,
                function(data){
                    $('#page_div').html(data);
                    alert("Rekod telah berjaya di masukkan") ;
                    self.close() ;
                    self.opener.refreshAgensi() ;
                },'html');
            }
        }
                
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
            var idHakmilik= '${actionBean.idHakmilik}';
            if(confirm("Adakah anda pasti?")) {
                var url = '${pageContext.request.contextPath}/pengambilan/sedia_jabatan?simpanAdun&item='+data+"&idHakmilik="+idHakmilik ;
                $.get(url,
                function(data){
                    $('#page_div').html(data);
                    alert("Rekod telah berjaya di masukkan") ;
                    self.close() ;
                    self.opener.refreshAgensi() ;
                },'html');
            }
        }
                
        function addSize(){
            var test = 45 ;
            for(var a = 0 ; a < test ; a++){
                if(document.frm.checkmate[a].checked){
                    size++ ;
                }
                else {
                    size-- ;
                }
                alert(size) ;
            }
        }
            
        function justClick() {
            if($('#pilihAgensi').val() == 'JTK'){
                $('#jtk').show() ;
                $('#jtk1').show() ;
                $('#adn').hide() ;
                $('#adn1').hide() ;
                document.getElementById('selectedAgensi').value  = $('#pilihAgensi').val()
            }
            else if ($('#pilihAgensi').val() == 'ADN'){
                $('#adn').show() ;
                $('#adn1').show() ;
                $('#jtk').hide() ;
                $('#jtk1').hide() ;
                document.getElementById('selectedAgensi').value  = $('#pilihAgensi').val()
            }
        }
</script>
<s:errors/>
<s:messages/>
<div class="subtitle">
    <s:form beanclass="etanah.view.stripes.pengambilan.JabatanTeknikalPengambilanActionBean" name="frm">
        <fieldset class="aras1">
            <legend>
                Kod Agensi
            </legend>           
            <s:hidden id="selectedKod" name="selectedKod" />
            <s:hidden id="selectedNama" name="selectedNama" />
            <s:hidden id="selectedAgensi" name="selectedAgensi"/>
            <p>
                <s:hidden name="index" id="index" />
            </p>
            <c:if test="${actionBean.kNegeri eq '04'}">
                <p>
                    <label>Kategori Agensi :</label>
                    <s:select name="pilihAgensi" id="pilihAgensi" onchange="justClick();">
                        <s:option value="">Sila Pilih</s:option>
                        <s:option value="JTK">Jabatan Teknikal</s:option>
                        <s:option value="ADN">Adun</s:option>
                    </s:select>
                </p>
            </c:if>
            <c:if test="${actionBean.kNegeri eq '05'}">
                <p>
                    <label>Kategori Agensi :</label>
                    <s:select name="pilihAgensi" id="pilihAgensi" onchange="justClick();">
                        <s:option value="">Sila Pilih</s:option>
                        <s:option value="JTK">Jabatan Teknikal</s:option>
                    </s:select>
                </p>
            </c:if>
            <div id="jtk">                

                <p>
                    <label>Kod :</label>
                    <s:text name="kod" id="kod"/>
                </p>
                <p>
                    <label>Nama Jabatan :</label>
                    <s:text name="kodAgensiNama" id="kodAgensiNama"  onkeyup="javascript:uppercase();"/>
                </p>


                <p><label>&nbsp;</label>
                    <s:submit name="cariKodAgensiJTK" value="Cari" class="btn"/>
                    <s:button name="tutup" value="Tutup" class="btn" onclick="javascript:window.close();"/>
                </p>
            </div>
            <div id="adn">                
                <p>
                    <label>Kod :</label>
                    <s:text name="kod" id="kod"/>
                </p>
                <p>
                    <label>Nama Adun :</label>
                    <s:text name="kodAgensiNama" id="kodAgensiNama"/>
                </p>
                <p><label>&nbsp;</label>
                    <s:submit name="cariKodAgensiADN" value="Cari" class="btn"/>
                    <s:button name="tutup" value="Tutup" class="btn" onclick="javascript:window.close();"/>
                </p>
            </div>
        </fieldset>
    </div>
    <br>

    <div class="subtitle">
        <fieldset class="aras1">
            <div id="jtk1">
                <s:hidden name="idHakmilik" value="${actionBean.idHakmilik}"/>
                <s:hidden name="${actionBean.sizeKod}" id="sizeKod" value="${actionBean.sizeKod}"/>
                ${actionBean.sizeKod}
                <p>
                    <display:table style="width:100%" class="tablecloth" name="${actionBean.listKodAgensi}" pagesize="100" cellpadding="0" cellspacing="0" requestURI="/pengambilan/sedia_jabatan" id="line">
                        <display:column> <s:checkbox name="checkmate" id="checkmate" value="${line.kod}"/></display:column>
                        <display:column title="Kod" property="kod"/>
                        <display:column title="Nama Jabatan Teknikal" property="nama" style="text-transform:uppercase;"/>
                    </display:table>
                </p>
                <c:if test="${fn:length(actionBean.listKodAgensi) > 0}" >
                    <c:if test="${agensi eq true}">
                        <p><label>&nbsp;</label>                            
                            <s:button name="simpanAgensi" value="Simpan" class="btn" onclick="javascript:selectCheckBox();"/>
                        </p>
                    </c:if>
                </c:if>
            </div>
            <div id="adn1">
                <s:hidden name="idHakmilik" value="${actionBean.idHakmilik}"/>
                <s:hidden name="${actionBean.sizeKod}" id="sizeKod" value="${actionBean.sizeKod}"/>
                <c:set var="i" value="1" />
                <c:set var="test" value="${actionBean.namaKawasan}"/>
                <p>
                    <display:table style="width:100%" class="tablecloth" name="${actionBean.listKodAgensiAdun}" pagesize="100" cellpadding="0" cellspacing="0" requestURI="/pelupusan/sedia_jabatan" id="line">
                        <display:column> <s:radio name="radiomate" id="radiomate" value="${line.kod}"/></display:column>
                        <display:column title="Kod" property="kod"/>
                        <display:column title="Nama Adun" property="nama"/>
                        <display:column title="Kawasan"> ${test[i-1]}
                        </display:column>
                        <c:set var="i" value="${i+1}" />
                    </display:table>
                </p>
                <c:if test="${fn:length(actionBean.listKodAgensiAdun) > 0}" >
                    <c:if test="${agensi eq true}">
                        <p><label>&nbsp;</label>                            
                            <s:button name="simpanAgensi" value="Simpan Adun" class="btn" onclick="javascript:selectRadioBox();"/>
                        </p>
                    </c:if>
                </c:if>
            </div>
        </fieldset>
    </div>
</s:form>
