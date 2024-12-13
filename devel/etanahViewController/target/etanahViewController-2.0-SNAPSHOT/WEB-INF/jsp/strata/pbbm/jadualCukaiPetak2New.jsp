<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">
    function doViewReport(v) {
    <%--var url = '${pageContext.request.contextPath}/dokumen/view/' + v;
    window.open(url, 'etanah2', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");--%>
        var idmohon = '${actionBean.mohonHakmilik.permohonan.idPermohonan}';
        var report = 'STRJadualPetak_MLK.rdf';
        var url = "reportName=" + report + "%26report_p_id_mohon=" + idmohon;
        //alert(url);
        window.open("${pageContext.request.contextPath}/common/pdf_viewer?pdf_url=" + url, "eTanah",
                "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");
    }

    function save(event, f) {

        $.blockUI({
            message: $('#displayBox'),
            css: {
                top: ($(window).height() - 50) / 2 + 'px',
                left: ($(window).width() - 50) / 2 + 'px',
                width: '50px'
            }
        });
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url, q,
                function (data) {
                    $('#page_div').html(data);
                    $.unblockUI();
                }, 'html');
    }

    $(document).ready(function () {
        var luasPetak = 0.00;
        var luasPetakAksr = 0.00;
        var kadarPetak = 0.00;
        var totalCukai = 0.00;
        var len = $('.listHm').length;
//        var hm = '${actionBean.hm}';
        var hm = '${actionBean.mohon.senaraiHakmilik[0].hakmilik.idHakmilik}';
        for (var i = 0; i < len; i++) {
            luasPetak = $('#luas' + i).val();
            luasPetakAksr = $('#luasAksr' + i).val();
            kadarPetak = $('#cukai' + i).val();
            if (luasPetakAksr === undefined) {
                luasPetakAksr = 0.00;
            }

            var cukai = Math.ceil((parseFloat(luasPetak) + parseFloat(luasPetakAksr)) * kadarPetak);
            var kosRendah = '${actionBean.bngnPtk.kosRendah}';
            if (kosRendah === 'Y') {
                if (cukai < 10) {
                    cukai = 10;
                }
            }
            if (kosRendah === 'T') {
                if (cukai < 15) {
                    cukai = 15;
                }
            }
            if (hm.includes("GMM") || hm.includes("HMM")) {
                cukai = parseFloat(cukai) / 2;
            }

            $('#totalCukai' + i).val(cukai);
            totalCukai = totalCukai + cukai;
        }

        $('#jum').val(totalCukai);
    });
    
    function selectAll(a, x) {

        var size = '${fn:length(actionBean.hm)}';
        var len = $('.bil').length;

        $(':checkbox').each(function () {
            if (a.checked) {
                this.checked = true;
            } else {
                this.checked = false;
            }
        });
    }
    
    function appendAutoAll(intIndex) {
        var val = $('#idHakmilikDari' + intIndex).val();
        if (val == '') {
            document.getElementById("bilHakmilikSiri0").value = "";
        }
        else if (val != '') {
            appendAuto(val, intIndex);
        }
    }

    function appendAuto(val, id) {
        var bil = $('#bilHakmilikSiri' + id).val();
        var len = val.length;
        var intIndex = 0;
        var pre = "";

        if (val != '') {
            val = val.toUpperCase();
            $('#idHakmilikDari' + id).val(val);
            bil = bil - 1;
            if (parseInt(bil, 10) > 0) {
                for (intIndex = len - 1; intIndex >= 0; intIndex--) {
                    var c = val.charAt(intIndex);
                    if (c >= '0' && c <= '9') {
                        pre = c + pre;
                    } else {
                        break;
                    }
                }

                var h = val.substring(0, intIndex + 1);//temp                
                var val2 = parseInt(pre, 10) + parseInt(bil);
                var len = (pre.length - String(val2).length);
                if (String(val2).length < pre.length) {
                    for (var i = 0; i < len; i++) {
                        val2 = "0" + val2;
                    }
                }

                h = h + val2;
                if (!isNaN(val2)) {
                    $('#idHakmilikHingga' + id).val(h);
                }
            }
        } else {
            $('#idHakmilikHingga' + id).val('');
        }
    }

    function validateHakmilikBersiri(idx) {   
                var dr = $("#idHakmilikDari" + idx).val().toUpperCase();
                var ke = $("#idHakmilikHingga" + idx).val().toUpperCase();
                var val1 = $("#idHakmilikDari" + idx).val().toUpperCase();
                var val2 = $("#idHakmilikHingga" + idx).val().toUpperCase();
                $("#idHakmilikDari" + idx).val(val1);
                $("#idHakmilikHingga" + idx).val(val2);                
                frm = this.form;
                if (dr == null || dr == "" || ke == null || ke == ""){
                  alert("Sila isikan jumlah hakmilik di kotak 'Bil. Pilihan'");
                  return;
                }  
                
                var bilBersiri =  $("#bilHakmilikSiri0").val(); 
                var len = $('.listHm').length;
                
                for (var i = 1; i <= len; i++) {
                     var stringDepan = $('#checkbox' + i).val().replace("-" + i, "");
                    var pilih1 = $('#checkbox' + i).val().replace(stringDepan + "-", "");
                    if (dr == pilih1) {
                        document.getElementById("checkbox" + i).checked = true;
                        var total = i + (bilBersiri - 1);
                        for (var a = i; a <= total; a++) {
                        document.getElementById("checkbox" + a).checked = true;
                        }   
                    }
                }
    }
    
    function isNumberKey(evt)
    {
        evt = (evt) ? evt : window.event;
        var charCode = (evt.which) ? evt.which : evt.keyCode;
        if (charCode > 31 && (charCode < 48 || charCode > 57)) {
            return false;
        }
        return true;
    }
    
     function cleartext() {
        $('#bilHakmilikSiri0').val('');
        $('#idHakmilikDari0').val('');
        $('#idHakmilikHingga0').val('');
        $('input:checkbox').removeAttr('checked')
    }
    
    
</script>
<s:useActionBean beanclass = "etanah.view.ListUtil" var="listUtil"/>
<s:form beanclass="etanah.view.strata.JadualPetakExcel" id="jana_cukai">
    <s:messages/>
    <s:errors/>
    <fieldset class="aras1">
        <table>

            Jumlah Petak : ${fn:length(actionBean.listHM)}<br />
            Jumlah Petak Aksesori : ${fn:length(actionBean.listPetakAksr)}<br /><br />

            Jumlah Cukai : <s:text name="jum" id="jum" readonly="readonly" /> <br /><br />

            <fieldset>
                <br />
                <p><label>Jenis Syarat :</label>
                    <s:select name="kategoriBangunan" >
                        <s:option value="">Sila Pilih</s:option>
                        <s:option value="DC027">Perniagaan</s:option>
                        <s:option value="DC029">Kediaman</s:option>
                    </s:select>
                <p>
                <p><label>Pengkelasan Tanah :</label>
                    <s:select name="kelasTanah" >
                        <s:option value="" >Sila Pilih</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodKelasTanah}" value="kod" label="nama"/>
                    </s:select>
                <p>
                <p><label>Kos Rendah :</label>
                    <s:select  name="kosRendah" >
                        <s:option value="">Sila Pilih</s:option>
                        <s:option value="Y">YA</s:option>
                        <s:option value="T">TIDAK</s:option>
                    </s:select>
                <p>
                    <br />
                
                    <p align="left">   
                       <label>&nbsp;</label>Jumlah Bil. Pilihan 
                     <s:text name="bilHakmilikSiri" id="bilHakmilikSiri0" size="5"  maxlength="3" onkeypress="return isNumberKey(event)"/>   <!--   onchange="appendAutoAll('0');" onblur="appendAutoAll('0');"                         -->
                    </p>
                    <p align="center">                
                        Pilihan No dari
                        <s:text name="idHakmilikDari" size="5" id="idHakmilikDari0" onkeypress="return isNumberKey(event)" onchange="appendAutoAll('0');" />
                        hingga
                        <s:text name="idHakmilikHingga" size="5" id="idHakmilikHingga0" onkeyup="this.value=this.value.toUpperCase();" readonly="true" disabled="true"/>
                        <input type="button" value="Tanda Pilihan" onclick="validateHakmilikBersiri(0)" class="btn" />
                        <br>
                     </p>
                     <p>
                        <label>&nbsp;</label>
                        <s:textarea name="listHakmilikSiri" id="txtSiri" style="display:none"/>
                    </p>
                    <br />
                    <center>
                        <s:button class="btn" name="janaCukaiNew" value="Jana Cukai" onclick="save(this.name, this.form);"/>
                        <s:button class="btn" name="simpanCukai" value="Simpan Cukai" onclick="save(this.name, this.form);"/>
                        <s:button class="btn" name="" value="Isi Semula" onclick="cleartext('jana_cukai');"/>
                   </center>

                <br />
            </fieldset>
            <br />
            <lagend><font color="blue" ><h4>Pengiraan Cukai Petak </h4></font></lagend>
            <p>
                <c:set value="0" var="count1"/>
                <c:set value="0" var="count"/>
                <display:table style="width:100%" class="tablecloth" name="${actionBean.hm}" id="line">
                    <display:column title="<br/><input type='checkbox' id='semua' name='semua' onclick='javascript:selectAll(this,${line_rowNum});' class='readonly'/>">
                    <%--<display:column title="">--%>   
                        <center><s:checkbox name="checkbox" value="${line[0]}-${line_rowNum}" id="checkbox${line_rowNum}"  class="readonly" /></center>
                    </display:column>
                    <display:column title="Pilihan</br>No">                        
                        <c:out value="${line_rowNum}"/>
                    </display:column>
                    <display:column title="Id Hakmilik Strata" class="listHm">
                        ${line[0]}
                        <s:hidden name="ptk${count}" id="ptk${count}" value="${line[0]}" />
                        <s:hidden name="hm${line_rowNum}" id="hm${line_rowNum}" value="${line[0]}" />
                    </display:column>
                    <c:set value="0" var="total"/>
                    <display:column title="a - Luas Petak (m²)">
                        <c:set value="${total + line[1]}" var="total"/>
                        ${line[1]}
                        <s:hidden name="luas" id="luas${count}" value="${line[1]}" />
                    </display:column>
                    <display:column title="b - Luas Petak Aksesori (m²)">
                        ${line[2]}
                        <s:hidden name="luasAksr" id="luasAksr${count}" value="${line[2]}" />
                    </display:column>
                    <display:column title="Jenis Bangunan">
                        <!--${line[3]}-->
                            <c:if test="${line[3] == 'DC029'}" >
                                KEDIAMAN
                                <s:hidden name="kodKegunaanBangunan" id="kodKegunaanBangunan${count}" value="${line[3]}" />
                            </c:if>
                            <c:if test="${line[3] == 'DC027'}" >    
                                PERNIAGAAN
                                <s:hidden name="kodKegunaanBangunan" id="kodKegunaanBangunan${count}" value="${line[3]}" />        
                            </c:if>
                        <s:hidden name="kodKegunaanBangunan" id="kodKegunaanBangunan${count}" value="${line[3]}" />
                    </display:column>
                    <display:column title="Kelas Tanah">
                        <!--${line[4]}-->
                        <c:if test="${line[4] == '1'}" >
                            Bandar I
                            <s:hidden name="kodkelasTanah" id="kodkelasTanah${count}" value="${line[4]}" />
                        </c:if>
                        <c:if test="${line[4] == '2'}" >
                            Bandar II
                            <s:hidden name="kodkelasTanah" id="kodkelasTanah${count}" value="${line[4]}" />
                        </c:if>
                        <c:if test="${line[4] == '3'}" >
                             Pekan, Desa1, Desa II   
                             <s:hidden name="kodkelasTanah" id="kodkelasTanah${count}" value="${line[4]}" />
                        </c:if>
                        <c:if test="${line[4] == '4'}" >
                             Desa III 
                             <s:hidden name="kodkelasTanah" id="kodkelasTanah${count}" value="${line[4]}" />
                        </c:if>
                    </display:column>
                    <display:column title="Kos Rendah">
                        <!--${line[5]}-->
                        <c:if test="${line[5] == 'Y'}" >
                                Ya
                                <s:hidden name="kosRendah" id="kosRendah${count}" value="${line[5]}" />
                            </c:if>
                            <c:if test="${line[5] == 'T'}" >    
                                Tidak
                                <s:hidden name="kosRendah" id="kosRendah${count}" value="${line[5]}" />        
                        </c:if>
                    </display:column>
                    <display:column title="Kadar">
                        ${line[6]}
                        <s:hidden name="cukai" id="cukai${count}" value="${line[6]}" />
                    </display:column>
                    
                    <display:column title="Cukai Petak - kadar*(a+b)" >
                        <s:text name="totalCukai${count}" id="totalCukai${count}" class="total" readonly="readonly"/>
                        <s:hidden name="totalCukai" id="totalCukai${line_rowNum-1}" value="${totalCukai}" />
                    </display:column>
                                
                    <display:column title="Sekatan">${line[7]}</display:column>

                    <c:set value="${count +1}" var="count"/>
                </display:table>
            </p>
        </table>
    </fieldset>
</s:form>

