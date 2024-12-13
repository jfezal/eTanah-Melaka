<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
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
        var len = $('.listPetak').length;
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

        var size = '${fn:length(actionBean.listPetak)}';
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
            alert("Sila isikan jumlah petak di kotak 'Bil. Pilihan'");
            return;
        }  
                
        var bilBersiri =  $("#bilHakmilikSiri0").val(); 
        var len = $('.listPetak').length;
                
        for (var i = 1; i <= len; i++) {
            var stringDepan = $('#checkbox' + i).val().replace("-" + i, "");
            var pilih1 = $('#checkbox' + i).val().replace(stringDepan + "-", "");
            if (dr == pilih1){ 
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

            Jumlah Petak : ${fn:length(actionBean.listPetak)}<br />
            Jumlah Petak Aksesori : ${fn:length(actionBean.listPetakAksesori)}<br /><br />
            
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
                        <s:button class="btn" name="janaCukaiPetak" value="Jana Cukai" onclick="save(this.name, this.form);"/>
                        <s:button class="btn" name="" value="Isi Semula" onclick="cleartext('jana_cukai');"/>
                    </center>

                <br />
            </fieldset>
            <br />
            <c:if test="${fn:length(actionBean.listPetak) > 0}">
                <lagend><font color="blue" ><h4>Pengiraan Cukai Petak </h4></font></lagend>
                <p>
                    <c:set value="0" var="count1"/>
                    <c:set value="0" var="count"/>
                    <display:table style="width:80%" class="tablecloth" name="${actionBean.listPetak}" id="line">
                        <display:column title="<br/><input type='checkbox' id='semua' name='semua' onclick='javascript:selectAll(this,${line_rowNum});' class='readonly'/>">
                        <center><s:checkbox name="checkbox" value="${line.nama}-${line.tingkat.idTingkat}-${line_rowNum}" id="checkbox${line_rowNum}"  class="readonly" /></center>
                        <s:hidden name="hiddenTingkat" id="hiddenTingkat${line_rowNum-1}" value="${line.tingkat.idTingkat}"/>
                        </display:column>
                        <display:column title="Pilihan</br>No">                        
                            <c:out value="${line_rowNum}"/>
                        </display:column>
                        <display:column title="Petak Strata" class="listPetak" >
                            ${line.nama}
                            <s:hidden name="ptk${count}" id="ptk${count}" value="${line.nama}" />
                            <s:hidden name="hiddenPetak" id="hiddenPetak${line_rowNum-1}" value="${line.nama}"/>
                            <s:hidden name="ptk${line_rowNum-1}" id="ptk${line_rowNum-1}" value="${line.nama}"/>
                            <s:hidden name="hideTingkat" id="hideTingkat${line_rowNum-1}" value="${line.tingkat.idTingkat}"/>
                        </display:column>
                        <c:set value="0" var="total"/>
                        <display:column title="a - Luas Petak (m²)">
                            <c:set value="${total + line.luas}" var="total"/>
                            ${line.luas}
                            <s:hidden name="luas" id="luas${count}" value="${line.luas}" />
                        </display:column>
                        <display:column title="b - Luas Petak Aksesori (m²)">
                            <c:forEach items="${actionBean.sumLuasByPetak}" var="items" begin="${count1}" end="${count}">
                                <c:if test="${items[1] == line.idPetak}">
                                    ${items[0]}
                                    <c:set value="${total + items[0]}" var="total"/>
                                    <s:hidden name="luasAksr" id="luasAksr${count}" value="${items[0]}" />
                                </c:if>
                            </c:forEach>
                        </display:column>
                        <display:column title="Jenis Bangunan" >
                            <!--${line.kodKegunaanBangunan.nama}-->
                            <%--<s:hidden name="kodKegunaanBangunan" id="kodKegunaanBangunan${count}" value="${line.kodKegunaanBangunan.kod}" />--%>
                            <c:if test="${line.kodKegunaanBangunan.kod == 'DC029'}" >
                                KEDIAMAN
                                <s:hidden name="kodKegunaanBangunan" id="kodKegunaanBangunan${count}" value="line.kodKegunaanBangunan.kod" />
                            </c:if>
                            <c:if test="${line.kodKegunaanBangunan.kod == 'DC027'}" >    
                                PERNIAGAAN
                                <s:hidden name="kodKegunaanBangunan" id="kodKegunaanBangunan${count}" value="line.kodKegunaanBangunan.kod" />        
                            </c:if>
                        </display:column>
                        <display:column title="Kelas Tanah" >
                            ${line.kodkelasTanah.nama}
                            <s:hidden name="kodkelasTanah" id="kodkelasTanah${count}" value="${line.kodkelasTanah.kod}" />
                        </display:column>
                        <display:column title="Kos Rendah" >
                            <c:if test="${line.kosRendah == 'Y'}" >
                                Ya
                                <s:hidden name="kosRendah" id="kosRendah${count}" value="${line.kosRendah}" />
                            </c:if>
                            <c:if test="${line.kosRendah == 'T'}" >    
                                Tidak
                                <s:hidden name="kosRendah" id="kosRendah${count}" value="${line.kosRendah}" />        
                            </c:if>
                        </display:column>
                        <display:column title="Kadar" >
                            ${line.kadar}
                            <s:hidden name="cukai" id="cukai${count}" value="${line.kadar}" />
                        </display:column>
                        <c:if test="${line.kadar == null}" >
                            ${line.cukai}
                        </c:if>
                        <c:if test="${line.kadar != null}" >
                        <display:column title="Cukai Petak - kadar*(a+b)" >
                            <s:text name="totalCukai${count}" id="totalCukai${count}" class="total" readonly="readonly" />
                            <s:hidden name="totalCukai" id="totalCukai${line_rowNum-1}" value="${totalCukai}" />
                        </display:column>
                        </c:if>
                    <c:set value="${count +1}" var="count"/>
                    </display:table>
                </p>
            </c:if>
        </table>
    </fieldset>
</s:form>

