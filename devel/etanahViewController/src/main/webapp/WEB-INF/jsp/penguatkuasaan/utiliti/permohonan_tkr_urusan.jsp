<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript" src="/etanah/pub/js/ageCalculator.js"></script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/styles/styles.css" />
        <title>Permohonan Tukar Urusan</title>

    </head>
    <body>
        <script language="javascript">

            $(document).ready(function() {
                $('input:text').each(function(){
                    $(this).focus(function() { $(this).addClass('focus')});
                    $(this).blur(function() { $(this).removeClass('focus')});
                });
                $('select').each(function(){
                    $(this).focus(function() { $(this).addClass('focus')});
                    $(this).blur(function() { $(this).removeClass('focus')});
                });

                $('form').submit(function(){
                    doBlockUI();
                    var valid = false;
                    $('.kodUrusan').each(function(index){
                        updateSelect(index);
                        var val = $('#kodUrusanKod' + index).val();
                        if (val != ''){
                            valid = true;
                        }
                        if (val == ''){ valid = false;}
                    });
                    doUnBlockUI();
                    return valid;
                });
            });

            function validate(f){

                var kodUrusan = document.form1.kodUrusan.value;
                var sebab = document.form1.sebab.value;
                if ((kodUrusan == ""))
                {
                    alert('Sila Pilih Kod Urusan ');
                    document.form1.kodUrusan.focus();
                }
                else if ((sebab == ""))
                {
                    alert('Sila Masukkan Sebab ');
                    document.form1.sebab.focus();
                }
                else{
                    if(confirm('Adakah anda pasti?')) {
                        f.action = f.action + '?changeUrusan';
                        f.submit();
                    }
                }
            }

            function updateSelect(idx){
                var textKodUrusanKod = document.getElementById('kodUrusanKod' + idx);
                if (textKodUrusanKod.value.length > 0){
                    var selectKodUrusan = document.getElementById('kodUrusan' + idx);
                    selectKodUrusan.selectedIndex = 0;
                    var kod = textKodUrusanKod.value.toUpperCase();
                    for (var i = 0; i < selectKodUrusan.options.length; ++i){
                        if (selectKodUrusan.options[i].value == kod){
                            selectKodUrusan.selectedIndex = i;
                            updateJabatan(idx, selectKodUrusan.options[i].parentNode.label);
                            break;
                        }
                    }
                    if (selectKodUrusan.selectedIndex == 0){
                        $('#kodUrusanKod'+ idx).val('');
                        alert('Kod Urusan ' + kod + ' tidak dijumpai.');
                        $('#kodUrusanKod'+ idx).focus();
                    }
                }
            }

            function updateKod(i){
                var selectKodUrusan = document.getElementById('kodUrusan' + i);
                if (selectKodUrusan.selectedIndex > 0){
                    var textKodUrusanKod = document.getElementById('kodUrusanKod' + i);
                    textKodUrusanKod.value = selectKodUrusan.options[selectKodUrusan.selectedIndex].value;
                    updateJabatan(i, selectKodUrusan.options[selectKodUrusan.selectedIndex].parentNode.label);
                }
            }

            function updateJabatan(whichItem, namaJabatan){
                var selectJabatan = document.getElementById('kodJabatan' + whichItem);
                for (i = 0; i < selectJabatan.length; i++){
                    if (selectJabatan.options[i].text == namaJabatan){
                        selectJabatan.selectedIndex = i;
                        break;
                    }
                }
            }

            function selectUrusanForJabatan(whichItem){
                var kodJabatan = $("#kodJabatan" + whichItem + " option:selected").text();

                var found = false;
                var selectUrusan = document.getElementById("kodUrusan" + whichItem);
                for (i = 0; i < selectUrusan.length; i++){
                    if (selectUrusan.options[i].parentNode.label == kodJabatan){
                        selectUrusan.selectedIndex = i;
                        found = true;
                        break;
                    }
                }

                if (!found) selectUrusan.selectedIndex = 0;
            }

            function doBlockUI () {
                $.blockUI({
                    message: $('#displayBox'),
                    css: {
                        top:  ($(window).height() - 50) /2 + 'px',
                        left: ($(window).width() - 50) /2 + 'px',
                        width: '50px'
                    }
                });
            }

            function doUnBlockUI (){
                $.unblockUI();
            }


        </script>
        <s:messages />
        <s:errors />

        <s:form action="/penguatkuasaan/utiliti_tukar_urusan" name="form1">
            <s:hidden name="permohonan.idPermohonan"></s:hidden>
            <fieldset class="aras1">

                <br>
                <legend>Permohonan Tukar Urusan</legend>
                <br>
                <display:table name="${actionBean.permohonan}" class="tablecloth">
                    <display:column property="idPermohonan" title="Id Permohonan" />
                    <display:column property="kodUrusan.kod" title="Kod Urusan" />
                    <display:column property="kodUrusan.nama" title="Nama Urusan"/>
                    <display:column property="kodUrusan.caj" title="Caj Bayaran"/>
                    <display:column property="infoAudit.tarikhMasuk" format="{0,date,dd/MM/yyyy}" title="Tarikh Perserahan/Permohonan" />
                </display:table>
                <br>
                
                <legend>Maklumat Urusan Baru</legend>

                <c:set scope="request" var="pilihanKodUrusan" value="${actionBean.pilihanKodUrusan}" />
                <c:set scope="request" var="senaraiJabatan" value="${listUtil.senaraiKodJabatan}" />

                <p><label for="kodUrusankod"><em>*</em>Urusan</label><nobr>
                    <s:text name="kodUrusan" id="kodUrusanKod0" onblur="javascript:updateSelect(0);" size="6" class="kodUrusan" onkeyup="this.value=this.value.toUpperCase();"/>
                    <c:set scope="request" var="jabatanNama" value="${pilihanKodUrusan[0].jabatanNama}" />
                    <s:select name="senaraiUrusan[0].kodUrusanPilih" id="kodUrusan0" onchange="javascript:updateKod(0);" style="width:400px;" >
                        <s:option label="Pilih Urusan..."  value="0" />
                        <c:forEach items="${actionBean.kodUrusanEnf}" var="i" >
                            <s:option value="${i.kod}" >${i.kod} - ${i.nama}</s:option>
                        </c:forEach>
                    </s:select>
                </p>
                <p>
                    <label><em>*</em>Sebab Pertukaran :</label>
                    <s:textarea name="sebab" cols="80" rows="10"/>
                    &nbsp;
                </p>
                <p>
                    <label>&nbsp;</label>
                    <s:button class="btn" name="simpan" value="Simpan" onclick="return validate(this.form);"/>
                </p>

            </fieldset>


        </s:form>

    </body>
</html>