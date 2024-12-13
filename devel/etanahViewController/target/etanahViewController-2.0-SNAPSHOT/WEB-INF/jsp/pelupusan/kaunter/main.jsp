<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

<stripes:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/pub/styles/styles.css" />
        <title>Belakang Kaunter | Utama</title>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
        <script language="javascript">

            $(document).ready(function() {
                $('input:text').each(function() {
                    $(this).focus(function() {
                        $(this).addClass('focus')
                    });
                    $(this).blur(function() {
                        $(this).removeClass('focus')
                    });
                });
                $('select').each(function() {
                    $(this).focus(function() {
                        $(this).addClass('focus')
                    });
                    $(this).blur(function() {
                        $(this).removeClass('focus')
                    });
                });

                $('form').submit(function() {
                    doBlockUI();
                    var valid = false;        
                    var id = $('#idPermohonan').val();
                    if (id === '') {
                        $('.kodUrusan').each(function(index) {
                            updateSelect(index);
                            var val = $('#kodUrusanKod' + index).val();
                            if (val != '') {
                                valid = true;
                            }
                            if (val == '') {
                                valid = false;                                                               
                            }
                        });
                    } else {
                        valid = true;
                    }
                    
                    if (!valid) doUnBlockUI(); 
                    
                    return valid;
                });
            });

            function updateSelect(idx) {
                var textKodUrusanKod = document.getElementById('kodUrusanKod' + idx);
                if (textKodUrusanKod.value.length > 0) {
                    var selectKodUrusan = document.getElementById('kodUrusan' + idx);
                    selectKodUrusan.selectedIndex = 0;
                    var kod = textKodUrusanKod.value.toUpperCase();
                    for (var i = 0; i < selectKodUrusan.options.length; ++i) {
                        if (selectKodUrusan.options[i].value == kod) {
                            selectKodUrusan.selectedIndex = i;
                            updateJabatan(idx, selectKodUrusan.options[i].parentNode.label);
                            break;
                        }
                    }
                    if (selectKodUrusan.selectedIndex == 0) {
                        $('#kodUrusanKod' + idx).val('');
                        alert('Kod Urusan ' + kod + ' tidak dijumpai.');
                        $('#kodUrusanKod' + idx).focus();
                    }
                }
            }

            function updateKod(i) {
                var selectKodUrusan = document.getElementById('kodUrusan' + i);
                if (selectKodUrusan.selectedIndex > 0) {
                    var textKodUrusanKod = document.getElementById('kodUrusanKod' + i);
                    textKodUrusanKod.value = selectKodUrusan.options[selectKodUrusan.selectedIndex].value;
                    updateJabatan(i, selectKodUrusan.options[selectKodUrusan.selectedIndex].parentNode.label);
                }
            }

            function updateJabatan(whichItem, namaJabatan) {
                var selectJabatan = document.getElementById('kodJabatan' + whichItem);
                for (i = 0; i < selectJabatan.length; i++) {
                    if (selectJabatan.options[i].text == namaJabatan) {
                        selectJabatan.selectedIndex = i;
                        break;
                    }
                }
            }

            function selectUrusanForJabatan(whichItem) {
                var kodJabatan = $("#kodJabatan" + whichItem + " option:selected").text();

                var found = false;
                var selectUrusan = document.getElementById("kodUrusan" + whichItem);
                for (i = 0; i < selectUrusan.length; i++) {
                    if (selectUrusan.options[i].parentNode.label == kodJabatan) {
                        selectUrusan.selectedIndex = i;
                        found = true;
                        break;
                    }
                }

                if (!found)
                    selectUrusan.selectedIndex = 0;
            }

            function checking(inputTxt) {
                var a = $("#hakmilik").val();
                $.get("${pageContext.request.contextPath}/hasil/check_idhakmilik_accountNo?doCheckHakmilik&idHakmilik=" + a,
                        function(data) {
                            if (data == '1') {
                                $("#msg" + inputTxt).html("OK");
                            }
                            else if (data == '0') {
                                $("#hakmilik").val("");
                                alert("ID Hakmilik " + a + " tidak wujud!");
                            } else if (data == '2') {
                                $("#hakmilik").val("");
                                alert("Terdapat Notis 6A dalam ID Hakmilik " + a + " yang masih belum dilunaskan! Sila jelaskan segera.");
                            }
                        });
            }

            function doBlockUI() {
                $.blockUI({
                    message: $('#displayBox'),
                    css: {
                        top: ($(window).height() - 50) / 2 + 'px',
                        left: ($(window).width() - 50) / 2 + 'px',
                        width: '50px'
                    }
                });
            }

            function checkingPermohonan(id) {
                $.get("${pageContext.request.contextPath}/hasil/check_idhakmilik_accountNo?doCheckIDPermohonan&idPermohonan=" + id,
                        function(data) {
                            if (data == '1') {
//                            alert('Rayuan menggunakan ID permohonan '+id+' telah dibuat');
                                alert('ID permohonan ' + id + ' telah digunakan');
                                $("#idPermohonan").val("");
                                $('#nxt').attr("disabled", "true");
                            } else if (data === '2') {
                                alert('Permohonan tidak boleh dilakukan di Pejabat ini.');
                                $("#idPermohonan").val("");
                                $('#nxt').attr("disabled", "true");
                            } else {
                                $('#nxt').removeAttr("disabled");
                            }
                        });
            }

            function doUnBlockUI() {
                $.unblockUI();
            }
        </script>
    </head>
    <body>      
        <img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>
        <span class=title>Kaunter Utama</span><br/>
        <span class=instr>Medan bertanda <em>*</em> adalah mandatori. Sila pilih urusan atau taip kod pada ruang yang disediakan.</span><br/><br/>

        <stripes:messages />
        <stripes:errors />


        <!--  PERMOHOHONAN/PERSERAHAN-->

        <stripes:form action="/pelupusan/kaunter" id="main_kaunter">

            <fieldset class="aras1">

                <legend>Pendaftaran Urusan</legend>

                <c:set scope="request" var="pilihanKodUrusan" value="${actionBean.pilihanKodUrusan}" />
                <c:set scope="request" var="senaraiJabatan" value="${listUtil.senaraiKodJabatan}" />
                <c:set scope="request" var="senaraiUrusanPendaftaran" value="${listUtil.senaraiUrusanPelupusanBelakangKaunter}" />

                <p><label for="kodUrusankod"><em>*</em>Urusan 1</label><nobr>

                    <%--<stripes:select name="senaraiUrusan[0].kodJabatan" id="kodJabatan0" onchange="selectUrusanForJabatan(0)" style="width:130px">

                             <option value="0">Pilih Urusan...</option>
                        <option value="16">Pendaftaran</option>
                    </stripes:select>--%>

                    <stripes:text name="senaraiUrusan[0].kodUrusan" id="kodUrusanKod0" onblur="javascript:updateSelect(0);" size="6" class="kodUrusan" onkeyup="this.value=this.value.toUpperCase();"/>

                    <c:set scope="request" var="jabatanNama" value="${pilihanKodUrusan[0].jabatanNama}" />

                    <stripes:select name="senaraiUrusan[0].kodUrusanPilih" id="kodUrusan0" onchange="javascript:updateKod(0);"
                                    style="width:600px;" >
                        <%--<stripes:option label="Pilih Urusan..."  value="0" />
                            <optgroup label="${jabatanNama}" />
                    <c:forEach items="${pilihanKodUrusan}" var="i" >
                        <c:if test="${jabatanNama != i.jabatanNama}" >
                            <c:set var="jabatanNama" value="${i.jabatanNama}" />
                            <optgroup label="${jabatanNama}" />
                        </c:if>
                            <stripes:option value="${i.kod}" >${i.kod} - ${i.nama}</stripes:option>
                    </c:forEach>--%>

                        <stripes:option label="Pilih Urusan..."  value="0" />
                        <c:forEach items="${senaraiUrusanPendaftaran}" var="i" >
                            <stripes:option value="${i.kod}" >${i.kod} - ${i.nama}</stripes:option>
                        </c:forEach>
                    </stripes:select>
                </nobr>
            </p>

            <p align="middle">Untuk Urusan Berangkai</p>

            <p><label for="kodUrusankod">Urusan 2</label><nobr>

                <%--   <stripes:select name="senaraiUrusan[1].kodJabatan" id="kodJabatan1" onchange="selectUrusanForJabatan(1)" style="width:130px">
                       <option value="0">Pilih Urusan...</option>
                       <option value="16">Pendaftaran</option>
                  </stripes:select>--%>

                <stripes:text name="senaraiUrusan[1].kodUrusan" id="kodUrusanKod1" onblur="javascript:updateSelect(1);" size="6" class="kodUrusan" onkeyup="this.value=this.value.toUpperCase();"/>

                <stripes:select name="senaraiUrusan[1].kodUrusanPilih" id="kodUrusan1" onchange="javascript:updateKod(1);"
                                style="width:600px;" >
                    <stripes:option label="Pilih Urusan..."  value="0" />
                    <c:forEach items="${senaraiUrusanPendaftaran}" var="i" >
                        <stripes:option value="${i.kod}" >${i.kod} - ${i.nama}</stripes:option>
                    </c:forEach>
                </stripes:select>
            </nobr>
        </p>

        <p><label for="kodUrusankod">Urusan 3</label><nobr>

            <%--   <stripes:select name="senaraiUrusan[2].kodJabatan" id="kodJabatan2" onchange="selectUrusanForJabatan(2)" style="width:130px">
                   <option value="0">Pilih Urusan...</option>
                   <option value="16">Pendaftaran</option>
              </stripes:select>--%>

            <stripes:text name="senaraiUrusan[2].kodUrusan" id="kodUrusanKod2" onblur="javascript:updateSelect(2);" size="6" class="kodUrusan" onkeyup="this.value=this.value.toUpperCase();"/>

            <stripes:select name="senaraiUrusan[2].kodUrusanPilih" id="kodUrusan2" onchange="javascript:updateKod(2);"
                            style="width:600px;" >
                <stripes:option label="Pilih Urusan..."  value="0" />
                <c:forEach items="${senaraiUrusanPendaftaran}" var="i" >
                    <stripes:option value="${i.kod}" >${i.kod} - ${i.nama}</stripes:option>
                </c:forEach>            
            </stripes:select>
        </nobr>
    </p>

    <p><label for="kodUrusankod">Urusan 4</label><nobr>

        <%--<stripes:select name="senaraiUrusan[3].kodJabatan" id="kodJabatan3" onchange="selectUrusanForJabatan(3)" style="width:130px">
            <option value="0">Pilih Urusan...</option>
            <option value="16">Pendaftaran</option>
       </stripes:select>--%>

        <stripes:text name="senaraiUrusan[3].kodUrusan" id="kodUrusanKod3" onblur="javascript:updateSelect(3);" size="6" class="kodUrusan" onkeyup="this.value=this.value.toUpperCase();"/>

        <stripes:select name="senaraiUrusan[3].kodUrusanPilih" id="kodUrusan3" onchange="javascript:updateKod(3);" 
                        style="width:600px;" >
            <stripes:option label="Pilih Urusan..."  value="0" />
            <c:forEach items="${senaraiUrusanPendaftaran}" var="i" >
                <stripes:option value="${i.kod}" >${i.kod} - ${i.nama}</stripes:option>
            </c:forEach>
        </stripes:select>
    </nobr>
</p>

<p><label for="kodUrusankod">Urusan 5</label><nobr>

    <%--<stripes:select name="senaraiUrusan[4].kodJabatan" id="kodJabatan4" onchange="selectUrusanForJabatan(4)" style="width:130px">
        <option value="0">Pilih Urusan...</option>
        <option value="16">Pendaftaran</option>
   </stripes:select>--%>

    <stripes:text name="senaraiUrusan[4].kodUrusan" id="kodUrusanKod4" onblur="javascript:updateSelect(4);" size="6" class="kodUrusan" onkeyup="this.value=this.value.toUpperCase();"/>

    <stripes:select name="senaraiUrusan[4].kodUrusanPilih" id="kodUrusan4" onchange="javascript:updateKod(4);"
                    style="width:600px;" >
        <stripes:option label="Pilih Urusan..."  value="0" />
        <c:forEach items="${senaraiUrusanPendaftaran}" var="i" >
            <stripes:option value="${i.kod}" >${i.kod} - ${i.nama}</stripes:option>
        </c:forEach>
    </stripes:select>
</nobr>
</p>

<p>
    <label>&nbsp;</label>
    <stripes:submit name="Step2" value="Seterusnya" class="btn" id="step2"/>
</p>

</fieldset>

<stripes:submit name="updateUrusanJabatan" style="display:none;" />

</stripes:form>

<stripes:form action="/pelupusan/kaunter/kesinambungan" id="main_kesinambungan">

    <fieldset class="aras1">

        <legend>Kesinambungan Urusan</legend>

        <p><label for="idPermohonan">Id Permohonan/ID Perserahan :</label>
            <input type="text" name="permohonan.idPermohonan" id="idPermohonan" onkeyup="this.value = this.value.toUpperCase();" onblur="checkingPermohonan(this.value)"/>
        </p>

        <p>
            <label>&nbsp;</label>
            <stripes:submit name="checkPermohonan" value="Seterusnya" class="btn" id="nxt" disabled="true"/>
        </p>

    </fieldset>         

</stripes:form>

<br/>
</div>
</body>
</html>