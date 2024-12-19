<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

<stripes:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<c:set var="action" value="/strata/laporan"/>
<c:if test="${!empty tanpaBayaran}">
    <c:set var="action" value="/strata/laporan"/>
</c:if>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/pub/styles/styles.css" />
        <title>Kaunter: Utama</title>
        <script language="javascript">
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
                        alert('Kod Urusan ' + kod + ' tidak dijumpai.');
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

            function checking(inputTxt){
                var a = $("#hakmilik").val();
                $.get("${pageContext.request.contextPath}/hasil/check_idhakmilik_accountNo?doCheckHakmilik&idHakmilik=" + a,
                function(data){
                    if(data == '1'){
                        $("#msg" + inputTxt).html("OK");
                    }
                    else if(data =='0'){
                        $("#hakmilik").val("");
                        alert("ID Hakmilik " + a + " tidak wujud!");
                    }else if(data =='2'){
                        $("#hakmilik").val("");
                        alert("Terdapat Notis 6A dalam ID Hakmilik " + a + " yang masih belum dilunaskan! Sila jelaskan segera.");
                    }
                });
            }

            function reload(f) {                
//                f.action = '${pageContext.request.contextPath}/daftar/carian?reload';
                f.submit();
            }


        </script>
    </head>
    <body>
        <span class=title>Kaunter Utama</span><br/>
        <span class=instr>Medan bertanda <em>*</em> adalah mandatori.</span><br/><br/>
        <c:set scope="request" var="pilihanKodUrusan" value="${actionBean.pilihanKodUrusan}" />

        <stripes:messages />
        <stripes:errors />

        <stripes:form action="${action}">

            <fieldset class="aras1">

                <legend>Urusan Carian</legend>
                <p>
                    <label for="kodUrusanCarian">Jenis Carian :</label>
                    &nbsp;
                    <stripes:select name="jenisCarian" style="width:450px;" onchange="reload(this.form);">                                                
                        <c:if test="${empty tanpaBayaran}">
                            <stripes:option label="Pilih Jenis Carian..."  value="0" />
                            <stripes:option value="CS" label="Carian Persendirian"/>
                            <stripes:option value="SS" label="Salinan Sah"/>
                        </c:if>
                        <stripes:option value="CR" label="Carian Rasmi"/>
                    </stripes:select>
                </p>
                <p>
                    <stripes:hidden name="senaraiUrusan[0].kodUrusan" id="kodUrusanKod0" />
                    <label for="kodUrusanCarian">Kod Urusan :</label>
                    &nbsp;
                    <stripes:select name="senaraiUrusan[0].kodUrusanPilih" id="kodUrusan0" style="width:450px;" onchange="javascript:updateKod(0);">
                        <stripes:option label="Pilih Urusan..."  value="0" />                   
                        <c:forEach items="${pilihanKodUrusan}" var="i" >
                            <stripes:option value="${i.kod}" >${i.kod} - ${i.nama}</stripes:option>
                        </c:forEach>
                    </stripes:select><em>*</em>
                </p>
                <br/>
                
                <p>
                    <label>&nbsp;</label>
                    <c:if test="${empty tanpaBayaran}">
                        <stripes:submit name="Step1a" value="Seterusnya" class="btn" />
                    </c:if>
                    <c:if test="${tanpaBayaran eq 'true'}">
                        <stripes:submit name="Step2" value="Seterusnya" class="btn" />
                    </c:if>
                </p>
                <br/>

            </fieldset>

        </stripes:form>
    </body>
</html>