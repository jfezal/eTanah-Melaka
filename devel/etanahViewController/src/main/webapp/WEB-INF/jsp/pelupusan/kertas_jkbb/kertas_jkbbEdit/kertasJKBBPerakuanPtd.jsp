<%-- 
    Document   : kertasMMKPerakuanPtd
    Created on : Apr 16, 2013, 12:42:17 PM
    Author     : afham
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Perakuan Pentadbir Tanah</title>
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
   
    $(document).ready(function() {

    <c:if test="${actionBean.fasaPermohonan.keputusan ne null}">
            doFunc($('#ksn').val());
    </c:if> 
    <c:choose>
        <c:when test="${actionBean.fasaPermohonan.keputusan.kod eq 'SL'}">
                $('#syarat').show();
        </c:when>
        <c:when test="${actionBean.fasaPermohonan.keputusan.kod eq 'ST'}">
                $('#syarat').hide();
        </c:when>
        <c:otherwise>
                $('#syarat').hide();
        </c:otherwise>
    </c:choose>     
        }); //END OF READY FUNCTION
    
        function refreshpage2(type){
            //        alert(type);
            var url = '${pageContext.request.contextPath}/pelupusan/kertas_JKBBV2?refreshpage&type='+type;
            window.open(url, "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
        }
    
        function addRow(index,f)
        {   
            NoPrompt();
            var kodksn= document.getElementById("ksn").value;
            var kpsn = '';
            var ks = document.form.ksn;
            for(var i=0; i< ks.length; i++){
                if(ks[i].checked){
                    kpsn = ks[i].value;
                 
                }
            }
            
            if(kpsn != null){
                $('#syorKpsn').val(kpsn);
                $('#ksn').val(kpsn);
            }
            var url = '${pageContext.request.contextPath}/pelupusan/kertas_JKBBV2?tambahRow&index='+index+'&kodksn='+kodksn+'&ksn='+kpsn;
            window.open(url, "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
        }
        function deleteRow(idKandungan,f,tName)
        {   
            NoPrompt();
            if(confirm('Adakah anda pasti untuk menghapus data ini?')) {
                var q = $(f).formSerialize();
                $.post('${pageContext.request.contextPath}/pelupusan/kertas_JKBBV2?deleteRow&idKandungan='+idKandungan+'&tName='+tName+'&typeName=kPerakuanPtd',q,
                function(data){
                    $('#page_div').html(data);

                }, 'html');
                self.refreshpage2('kPerakuanPtd') ;
            }
        }
    
        function refreshpage(){
            //        alert('aa');
            NoPrompt();
            opener.refreshV2KertasJKBB('main');
            self.close();
        }
        
        function setKandungan(i,idKandungan){
            var index = i;
            var kandungan = $('#kandungan5'+index).val();
            $('#'+idKandungan+'kandunganKertas').val(kandungan);
        }
    
        function cariPopup(val){
            var idMh = $('#idMh').val();
            //            alert(idMh);
            NoPrompt();
            window.open("${pageContext.request.contextPath}/pelupusan/kertas_JKBBV2?searchSyarat&jenisSyarat="+val+"&idMh="+idMh, "eTanah",
            "status=1,toolbar=0,location=1,menubar=0,width=910,height=800");
        }
       
        
        function CurrencyFormatted(amount)
        {
            var i = parseFloat(amount);
            if(isNaN(i)) { i = 0.00; }
            var minus = '';
            if(i < 0) { minus = '-'; }
            i = Math.abs(i);
            i = parseInt((i + .005) * 100);
            i = i / 100;
            s = new String(i);
            if(s.indexOf('.') < 0) { s += '.00'; }
            if(s.indexOf('.') == (s.length - 2)) { s += '0'; }
            s = minus + s;
            return s;
        }
        function calculateBayarKupon(){

            var kuponQty = document.getElementById('kuponQty').value;

            var kuponAmaun = document.getElementById('kuponAmaun').value;
            var jumlah = (kuponAmaun * kuponQty);
            document.getElementById('kupon').value = CurrencyFormatted(jumlah);
            calculateSyarat();
        }
        function calculateSyarat(){
            var kuantiti = document.getElementById('kuantitiSyarat').value;
            //alert(kuantiti);
            var bayaran = document.getElementById('bayaranSyarat').value;
            //alert(bayaranSyarat);
            var jumlah = kuantiti * bayaran;
            //alert(jumlah);
            var cagaran = 20/100 * jumlah;
            var cagarJalan = document.getElementById('cagarJalan').value;

            var kuponQty = document.getElementById('kuponQty').value;

            var kuponAmaun = document.getElementById('kuponAmaun').value;
            var jumlahKpnQty = (kuponAmaun * kuponQty);
            cagarJalan = cagarJalan*1;
            var totalAll = (jumlah) + (cagaran) + (jumlahKpnQty) + (cagarJalan);

            document.getElementById('jumlahSyarat').value = CurrencyFormatted(jumlah);
            document.getElementById('cagaranSyarat').value = CurrencyFormatted(cagaran);
            document.getElementById('kuantitiJumlahSyarat').value = kuantiti;
            document.getElementById('cagarJalan').value = CurrencyFormatted(cagarJalan);
            document.getElementById('totalAll').value = CurrencyFormatted(totalAll);
        }
        
        function checkKandungan(i){
            var index = i;
            var kandungan = $('#kandungan5'+index).val();
            if(kandungan == 'Sila Tambah Ayat Di Sini'){
                $('#kandungan5'+index).val("");
            }
        }
        function doFunc(val){           

            $('#syarat').hide();
            setKpsn2(val);

        
        }
    
        function doFunc1(val){           
            $('#syarat').show();
            setKpsn2(val);
        
        }
    
        function setKpsn2(val){
            $('#ksn').val(val);
            $('#syorKpsn').val(val);
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
    <s:form beanclass="etanah.view.stripes.pelupusan.KertasJKBBV2ActionBean" name="form">
        <s:errors/>
        <s:messages/>    
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Perakuan Pentadbir Tanah</legend>
                <table class="tablecloth" border="0" align="center">
                    <tr>
                        <td>
                            Syor:
                        </td>
                        <td colspan="2">
                            <s:radio name="ksn" id="ksn" value="SL" onclick="doFunc1(this.value);" />&nbsp;Syor Lulus
                            <s:radio name="ksn" id="ksn" value="ST" onclick="doFunc(this.value);" />&nbsp;Syor Tolak
                            <s:hidden name="syorKpsn" id="syorKpsn"/>
                        </td>
                    </tr>
                    <c:set var="i" value="1" />
                    <c:set var="num" value="1"/>
                    <c:forEach items="${actionBean.senaraiKandunganPerakuanPtd}" var="line">
                        <tr>
                            <td style="text-align: right"><c:out value="${num}"/></td>
                            <td>
                                <s:textarea  id="kandungan5${i}" name="senaraiKandunganPerakuanPtd[${i-1}].kandungan" cols="80"  rows="5" onclick="checkKandungan(${i})" onblur="setKandungan(${i},${line.idKandungan})" class="normal_text"/>
                                <s:hidden id="${line.idKandungan}kandunganKertas" name="${line.idKandungan}kandunganKertas"/>
                                <s:hidden name="idKandungan1" id="idKandungan1" value="${line.idKandungan}"/>
                            </td>
                            <td style="vertical-align: middle;">
                                <s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow(${line.idKandungan},this.form,'mohonKertasKandungan')"></s:button> 
                                </td>
                            </tr>

                        <c:set var="i" value="${i+1}" />
                        <c:set var="num" value="${num+1}"/>
                    </c:forEach>
                    <tr id="syarat">
                        <td colspan="3">
                            <c:choose>
                                <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PBPTD'}">
                                    <c:import url="syarat/syaratBatuan.jsp"></c:import>
                                </c:when>
                                <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PPBB'}">
                                    <c:import url="syarat/syaratBatuan.jsp"></c:import>
                                </c:when>
                                <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PBPTG'}">
                                    <c:import url="syarat/syaratBatuan.jsp"></c:import>
                                </c:when>
                                <c:otherwise></c:otherwise>
                            </c:choose>
                        </td>
                    </tr>        
                    <tr>
                        <td style="text-align:center;" colspan="3">      
                            <s:button value="Tambah" class="btn" name="simpan1"  onclick="addRow('6',this.form)"/>
                            <s:hidden name="indexKertas" id="indexKertas" value="6"/>
                            <s:submit name="simpanKandungan" value="Simpan" class="btn" onclick="NoPrompt();"/>

                            <%--<s:button name="simpanSyor" value="Simpan" class="btn" onclick="menyimpan('5',${i-1},this.form)"/>--%>
                            <s:button name="tutup" value="Tutup" class="btn" onclick="refreshpage()"/>
                        </td>
                    </tr>
                </table>
            </fieldset>
        </div>
    </s:form>
</body>