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

    <c:choose>
        <c:when test="${actionBean.hakmilikPermohonan.kodHakmilik.kod eq 'GM' || actionBean.hakmilikPermohonan.kodHakmilik.kod eq 'GRN'}">
                $('#jikaPajakan').hide();
        </c:when>
        <c:when test="${actionBean.hakmilikPermohonan.kodHakmilik.kod eq 'PM' || actionBean.hakmilikPermohonan.kodHakmilik.kod eq 'PN'}">
                $('#jikaPajakan').show();
        </c:when>
        <c:otherwise>
                $('#jikaPajakan').hide();
        </c:otherwise>
    </c:choose> 
    <c:if test="${actionBean.hakmilikPermohonan.kategoriTanahBaru ne null}">
            filterKodGunaTanah();
    </c:if>
    <c:if test="${actionBean.fasaPermohonan.keputusan ne null}">
	<c:choose>
        <c:when test="${actionBean.fasaPermohonan.keputusan.kod eq 'SL'}">
				var ksn1 = "SL";
                doFunc1(ksn);
        </c:when>
        <c:when test="${actionBean.fasaPermohonan.keputusan.kod eq 'ST'}">
				var ksn1 = "ST";
				doFunc(ksn1);
        </c:when>
        <c:otherwise>
                $('#syarat').hide();
        </c:otherwise>
    </c:choose>
    </c:if> 
    <c:if test="${actionBean.fasaPermohonan.keputusan eq null}">
	$('#syarat').hide();
    </c:if>
    <c:if test="${actionBean.hakmilikPermohonan.kadarPremium ne 0}">
            $('#amnt').val(CurrencyFormatted(${actionBean.hakmilikPermohonan.kadarPremium}));
    </c:if>
    <c:choose>
        <c:when test="${actionBean.hakmilikPermohonan.statusLuasDiluluskan eq 'P'}">
                $('#luassbhgn').hide();
        </c:when>
        <c:when test="${actionBean.hakmilikPermohonan.statusLuasDiluluskan eq 'S'}">
                $('#luassbhgn').show();
        </c:when>
        <c:otherwise>
                $('#luassbhgn').hide();
        </c:otherwise>
    </c:choose>
 
        }); //END OF READY FUNCTION
    
        function refreshpage2(type){
            //        alert(type);
            var url = '${pageContext.request.contextPath}/pelupusan/kertas_MMKPPTR?refreshpage&type='+type;
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
            //if(kpsn != null){
            //    $('#syorKpsn').val(kpsn);
            //    $('#ksn').val(kpsn);
            //}
            if(kpsn != null){
                $('#syorKpsn').val(kpsn);
                $('#ksn').val(kpsn);
            }
            var url = '${pageContext.request.contextPath}/pelupusan/kertas_MMKPPTR?tambahRow&index='+index+'&kodksn='+kpsn+'&ksn='+kpsn;
            window.open(url, "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
        }
        function deleteRow(idKandungan,f,tName)
        {   
            NoPrompt();
            if(confirm('Adakah anda pasti untuk menghapus data ini?')) {
                var q = $(f).formSerialize();
                $.post('${pageContext.request.contextPath}/pelupusan/kertas_MMKPPTR?deleteRow&idKandungan='+idKandungan+'&tName='+tName+'&typeName=kPerakuanPtd',q,
                function(data){
                    $('#page_div').html(data);

                }, 'html');
                self.refreshpage2('kPerakuanPtd') ;
            }
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
    
        function cariPopup(val){
            var idMh = $('#idMh').val();
            //            alert(idMh);
            NoPrompt();
            window.open("${pageContext.request.contextPath}/pelupusan/kertas_MMKPPTR?searchSyarat&jenisSyarat="+val+"&idMh="+idMh, "eTanah",
            "status=1,toolbar=0,location=1,menubar=0,width=910,height=800");
        }
       
        
        function doSomething(val){
          
            if(val=='GRN'|| val=='GM'){
                //alert(val);
                $('#jikaPajakan').hide();
            }else if (val=='PN' || val=='PM'){
                //alert(val);
                $('#jikaPajakan').show();
            }
            
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
    
        function filterKodGunaTanah() {
            var katTanah = $("#_kodKatTanah").val();
            //        alert("hi");
            NoPrompt();
        
            $.post('${pageContext.request.contextPath}/pelupusan/laporan_tanahV2?senaraiKodGunaTanahByKatTanah&kodKatTanah=' + katTanah,
            function(data) {
                if (data != '') {
                    $('#partialKodGunaTanah').html(data);
                    //                    $.unblockUI();
                }
            }, 'html');

        }
      
        function kiraCukai(f) {
            var kodTanah = $("#kodTanah").val();
            var q = $(f).formSerialize();
            $.post('${pageContext.request.contextPath}/pelupusan/kertas_MMKPPTR?kiraCukai&kodTanah=' + kodTanah , q,
            function(data) {
                if (data == '-1') {
                } else {
                    $('#cukai').val("RM "+CurrencyFormatted(data)+ " sehektar");
                }
            }, 'html');
        }
        
        function checkKandungan(i){
            var index = i;
            var kandungan = $('#kandungan5'+index).val();
            if(kandungan == 'Sila Tambah Ayat Di Sini'){
                $('#kandungan5'+index).val("");
            }
        }
        function doFunc(val){           
		
			if (val == "SL"){
				var val = "ST";
			}
            $('#syarat').hide();
            setKpsn2(val);
			
        }
    
        function doFunc1(val){

			if (val == "ST"){
				var val = "SL";
			}
			$('#syarat').show();
            setKpsn2(val);
        }
    
        function setKpsn2(val){
            $('#ksn').val(val);
            $('#syorKpsn').val(val);
        }
        
        function calculateBayaranPRMP(){

            var kuponQty = document.getElementById('kadarBayarPRMP').value;
            //       alert(kuponQty);
            var kuponAmaun = document.getElementById('luasLulus').value;
            //        alert(kuponAmaun);
            var koduom = document.getElementById('kodU').value;
            var jumlah = 0;
            if(koduom == '0')
                alert('Sila Nyatakan Jenis Luas Diluluskan untuk pengiraan bayaran.');
            if(koduom == 'M')
                jumlah = kuponAmaun * kuponQty;
            if(koduom == 'H'){
                kuponQty = kuponQty * 10000;
                jumlah = kuponAmaun * kuponQty;
            }
            document.getElementById('amaun').value = CurrencyFormatted(jumlah);
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
			var ts = $('#syorKpsn').val();
			var ts2 = $('#ksn').val();
			$('#syorKpsn').val(ts2);
        }

    </script>
    <s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
    <s:form beanclass="etanah.view.stripes.pelupusan.KertasMMKPPTRActionBean" name="form">
        <s:errors/>
        <s:messages/>    
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Syor Pentadbir Tanah</legend>
                <table class="tablecloth" border="0" align="center">
                    <tr>
                        <td>
                            Syor:
                        </td>
                        <td colspan="2">
							<c:choose>
								<c:when test="${actionBean.ksn eq 'SL'}">
									<s:radio name="ksn" id="ksn" value="SL" checked="true" onclick="doFunc1(this.value);" />&nbsp;Syor Lulus
									<s:radio name="ksn" id="ksn" value="ST" onclick="doFunc(this.value);" />&nbsp;Syor Tolak
									<s:hidden name="syorKpsn" id="syorKpsn"/>
								</c:when>
								<c:when test="${actionBean.ksn eq 'ST'}">
									<s:radio name="ksn" id="ksn" value="SL" onclick="doFunc1(this.value);" />&nbsp;Syor Lulus
									<s:radio name="ksn" id="ksn" value="ST" checked="true" onclick="doFunc(this.value);" />&nbsp;Syor Tolak
									<s:hidden name="syorKpsn" id="syorKpsn"/>
								</c:when>
								<c:otherwise>
									<s:radio name="ksn" id="ksn" value="SL" onclick="doFunc1(this.value);" />&nbsp;Syor Lulus
									<s:radio name="ksn" id="ksn" value="ST" onclick="doFunc(this.value);" />&nbsp;Syor Tolak
									<s:hidden name="syorKpsn" id="syorKpsn"/>
								</c:otherwise>
							</c:choose>
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
                                <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PBMT'}">
                                    <c:import url="syarat/syaratPBMT.jsp"></c:import>
                                </c:when>
                                <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PPTR'}">
                                    <c:import url="syarat/syaratPPTR.jsp"></c:import>
                                </c:when>
                                <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PPRU'}">
                                    <c:import url="syarat/syaratPPRU.jsp"></c:import>
                                </c:when>
                                <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PRMP'}">
                                    <c:import url="syarat/syaratPRMP.jsp"></c:import>
                                </c:when>
                                <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'LPSP'}">
                                    <c:import url="syarat/syaratLPSP.jsp"></c:import>
                                </c:when>
                                <c:otherwise></c:otherwise>
                            </c:choose>
                        </td>
                    </tr>        
                    <tr>
                        <td style="text-align:center;" colspan="3">      
                            <s:button value="Tambah Ulasan" class="btn" name="simpan1"  onclick="addRow('5',this.form)"/>
                            <s:hidden name="indexKertas" id="indexKertas" value="5"/>
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
