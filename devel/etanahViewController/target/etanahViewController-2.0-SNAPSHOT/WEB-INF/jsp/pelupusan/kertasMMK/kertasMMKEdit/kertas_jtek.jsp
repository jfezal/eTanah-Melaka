<%-- 
    Document   : kertas_jtek
    Created on : Jul 8, 2015, 2:59:44 PM
    Author     : Erra Zyra
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Perihal Permohonan</title>
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
   
    $(document).ready(function() {
        //maximizeWindow();
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
    }); //END OF READY FUNCTION
    
    function refreshpage2(type){
        //        alert(type);
        var url = '${pageContext.request.contextPath}/pelupusan/kertas_MMKV2?refreshpage&type='+type;
        window.open(url, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
    }
    
    function addRow(index)
    {
        NoPrompt();
        var url = '${pageContext.request.contextPath}/pelupusan/kertas_MMKV2?tambahRowjtek&index='+index;
        window.open(url, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
    }
    function deleteRow(idRujukan,index)
    {
        NoPrompt();
        if(confirm('Adakah anda pasti untuk menghapus data ini?')) {

            NoPrompt();
            var url = '${pageContext.request.contextPath}/pelupusan/kertas_MMKV2?deleteRowjtek&idRujukan='+idRujukan+'&index='+index;
            window.open(url, "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
        }
    }

    function simpan(idRujukan, i, index)
    {
        var kandungan5 = $('#kandungan5'+i).val();
        NoPrompt();
        var url = '${pageContext.request.contextPath}/pelupusan/kertas_MMKV2?simpanjtek&idRujukan='+idRujukan+'&kandungan5='+kandungan5+'&index='+index;
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
        alert("Maklumat Telah Berjaya Disimpan");
    }

    function refreshpage(){
        //        alert('aa');
        NoPrompt();
        opener.refreshV2KertasMMK('main');
        self.close();
    }
        
    function setKandungan(i,idKandungan){
        var index = i;
        var kandungan = $('#kandungan5'+index).val();
        $('#'+idKandungan+'kandunganKertas').val(kandungan);
    }
        
    function checkKandungan(i){
        var index = i;
        var kandungan = $('#kandungan5'+index).val();
        if(kandungan == 'Sila Tambah Ayat Di Sini'){
            $('#kandungan5'+index).val("");
        }
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
<s:form beanclass="etanah.view.stripes.pelupusan.KertasMMKV2ActionBean" name="form">
    <s:errors/>
    <s:messages/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Ulasan-Ulasan Jabatan Teknikal</legend>
            <table class="tablecloth" border="0" align="center">
                <c:set var="i" value="1" />
                <c:set var="num" value="1"/>
                <c:forEach items="${actionBean.senaraiUlasanJabatanTeknikalKod}" var="line">
                    <tr>
                        <td>
                            <ol type="I" start="${num}">
                                <li></li>
                            </ol>
                        </td>
                        <td><font style="font-weight:bold;">${line.namaAgensi}</font></td>
                    </tr>
                    <tr>
                        <td style="text-align: right"><div id="res"></div></td>
                        <td>
                            <s:textarea  id="kandungan5${i}" name="senaraiUlasanJabatanTeknikalKod[${i-1}].ulasan" cols="80"  rows="5" class="normal_text"/>
                            <s:hidden id="${line.idRujukan}kandunganKertas" name="${line.idRujukan}kandunganKertas"/>
                            <s:hidden name="idRujukan" id="idRujukan" value="${line.agensi.kod}"/>
                        </td>
                        <td style="vertical-align: middle;">
                            <s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow('${line.idRujukan}','${line.agensi.kod}')"></s:button><br>
                            <s:button value="Tambah" class="btn" name="simpan1"  onclick="addRow(${line.agensi.kod})"/><br>
                            <s:button name="simpanjtek" value="Simpan" class="btn" onclick="simpan('${line.idRujukan}','${i}',${line.agensi.kod})"/>
                        </td>
                    </tr>

                    <c:set var="i" value="${i+1}" />
                    <c:set var="num" value="${num+1}"/>
                </c:forEach>
                <tr>
                    <td style="text-align:center;" colspan="3">
                        <s:hidden name="indexKertas" id="indexKertas" value="2"/>
                        <%--<s:button name="simpanSyor" value="Simpan" class="btn" onclick="menyimpan('5',${i-1},this.form)"/>--%>
                        <s:button name="tutup" value="Tutup" class="btn" onclick="refreshpage()"/>
                    </td>
                </tr>
            </table>
        </fieldset>
    </div>
</s:form>
</body>
