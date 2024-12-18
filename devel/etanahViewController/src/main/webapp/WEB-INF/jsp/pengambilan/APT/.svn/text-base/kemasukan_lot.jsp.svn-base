<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript">
    function validation() {
        if ($("#idH").val() == "") {
            alert('Sila masukkan " ID Hakmilik " terlebih dahulu.');
            $("#idH").focus();
            return true;
        }
    }

//     function copyAdd(){
//        if($('input[name=add1]').is(':checked')){
//            $('#suratAlamat1').val($('#alamat1').val());
//            $('#suratAlamat2').val($('#alamat2').val());
//            $('#suratAlamat3').val($('#alamat3').val());
//            $('#suratAlamat4').val($('#alamat4').val());
//            $('#suratPoskod').val($('#poskod').val());
//            $('#suratNegeri').val($('#negeri').val());
//        }else{
//            $('#suratAlamat1').val('');
//            $('#suratAlamat2').val('');
//            $('#suratAlamat3').val('');
//            $('#suratAlamat4').val('');
//            $('#suratPoskod').val('');
//            $('#suratNegeri').val('');
//
//        }
//    }



    function save1(event, f) {
        // if(validation()){

        // }
        // else{
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url, q,
                function(data) {
                    $('#page_div', opener.document).html(data);
//                    self.opener.refreshPageHakmilik();
                    self.close();
                }, 'html');
        // }
    }
    $(document).ready(function() {
        $("#close").click(function() {
            setTimeout(function() {
                self.close();
            }, 100);
        });
    });

    function filterBandarPekanMukim() {
        var kodDaerah = $("#daerah").val();
        alert(kodDaerah);
        $.post('${pageContext.request.contextPath}/pelupusan/utilitiReportNs?senaraiKodBPM&kodDaerah=' + kodDaerah,
                function(data) {
                    if (data != '') {
                        $('#partialKodBPM').html(data);
                        //                    $.unblockUI();
                    }
                }, 'html');

    }

    function moduledet(val) {
        //alert("TESTING");
        $.get('${pageContext.request.contextPath}/pengambilan/maklumat_lotpengambilan?findKodSeksyen&kod=' + val,
                function(data) {
                    $("#kodS").replaceWith($('#kodS', $(data)));
                }, 'html');
    }

    function copyAdd(id) {
//        alert("Testing");
//        alert(id);
        var text = document.getElementById("area");
        var text1 = document.getElementById("area2");
//        alert(text1);
        if ($('input[name=add1]').is(':checked')) {

//            $('#noLotView').hide();
//            $('#noLot').show();
            text1.style.display = "block";
            text.style.display = "none";
//            alert("Testing2");
//            $('.noLot_' + id).attr('disabled', '');
        } else {
//            alert("test");
            text.style.display = "block";
            text1.style.display = "none";
        }
    }



</script>


<div class="subtitle">
    <%--<s:form beanclass="etanah.view.stripes.common.PihakBerkepentinganActionBean" >--%>
    <s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
    <s:form beanclass="etanah.view.stripes.pengambilan.share.common.MaklumatPlotActionBean" > <fieldset class="aras1">
            <legend>
                Kemasukan Hakmilik/Lot
            </legend>
            <p>
                <label for="Id Hakmilik">ID Hakmilik :</label>
                <s:hidden name="idMH" value="${actionBean.hakmilikPermohonan.id}"/>

                <c:if test="${actionBean.hakmilikPermohonan.hakmilik ne null}">
                    <s:text name="idH" id="idH" disabled ="true" value="${actionBean.hakmilikPermohonan.hakmilik.idHakmilik}"/>
                    <s:hidden name="idH" id="idH" value="${actionBean.hakmilikPermohonan.hakmilik.idHakmilik}"/>
                </c:if>
                <c:if test="${actionBean.hakmilikPermohonan.hakmilik eq null}">
                    <s:text name="idH" id="idH" value="${actionBean.hakmilikPermohonan.hakmilik.idHakmilik}"/>
                </c:if>

            </p>
            <p>
                <label for="atau"><i><em>atau</em></i></label>
            </p>
            <br>
            <c:if test="${actionBean.hakmilikPermohonan.hakmilik ne null}">
                <div class="row" id="area" style="display: none;" >
                    <p>
                        <label for="No Lot">No Lot :</label>
                        <s:select name="kodLot" disabled="true" class="kodLot" value="${actionBean.hakmilikPermohonan.lot.kod}">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${listUtil.senaraiLotPelupusan}" label="nama" value="kod" />
                        </s:select>
                        <s:text name="noLot" class="noLot" disabled="true" id="noLot" value="${actionBean.hakmilikPermohonan.noLot}"/>

                    </p> 
                </div>
                <div class="row" id="area2" style="display: none;" >
                    <p>
                        <label for="No Lot">No Lot :</label>
                        <s:select name="kodLot" disabled="false" class="kodLot" value="${actionBean.hakmilikPermohonan.lot.kod}">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${listUtil.senaraiLotPelupusan}" label="nama" value="kod" />
                        </s:select>
                        <s:text name="noLot" class="noLot" disabled="false" id="noLot" value="${actionBean.hakmilikPermohonan.noLot}"/>

                    </p> 
                </div>
            </c:if>
            <c:if test="${actionBean.hakmilikPermohonan.hakmilik eq null}">


                <p>
                    <label for="No Lot">No Lot :</label>
                    <s:select name="kodLot"  class="kodLot" value="${actionBean.hakmilikPermohonan.lot.kod}">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${listUtil.senaraiLotPelupusan}" label="nama" value="kod" />
                    </s:select>
                    <s:text name="noLot" class="noLot"  id="noLot" value="${actionBean.hakmilikPermohonan.noLot}"/>

                </p> 

            </c:if>




            <p>
                <label> Daerah :</label>
                ${actionBean.permohonan.cawangan.daerah.nama}
            </p>
            <c:if test="${actionBean.hakmilikPermohonan.hakmilik ne null}">
                <p>
                    <label> Mukim/Pekan/Bandar :</label>
                    <s:select name="bandarPekanMukimBaru" disabled="true" value="${actionBean.hakmilikPermohonan.bandarPekanMukimBaru.kod}" id="mpb" onchange="moduledet(this.value);">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${actionBean.senaraiKodBPM}" label="nama" value="kod" />
                    </s:select>
                </p>   
                <p>

                    <label> Seksyen  ${actionBean.hakmilikPermohonan.kodSeksyen.nama}:</label>
                    <s:select name="kodSeksyen" id="kodS"  style="width:40%;" value="kod">
                        <s:option  value="">Sila Pilih</s:option>
                        <c:forEach items="${actionBean.listKodSeksyen}" var="i" >
                            <s:option value="${i.kod}" >${i.nama}</s:option>
                        </c:forEach>
                    </s:select>
                </p>
            </c:if>
            <c:if test="${actionBean.hakmilikPermohonan.hakmilik eq null}">
                <p>
                    <label> Mukim/Pekan/Bandar :</label>
                    <s:select name="bandarPekanMukimBaru" value="${actionBean.hakmilikPermohonan.bandarPekanMukimBaru.kod}" id="mpb" onchange="moduledet(this.value);">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${actionBean.senaraiKodBPM}" label="nama" value="kod" />
                    </s:select>
                </p>   
                <p>
                    <label> Seksyen :</label>
                    <s:select name="kodSeksyen" id="kodS"  style="width:40%;" value="kod">
                        <s:option  value="">Sila Pilih</s:option>
                        <c:forEach items="${actionBean.listKodSeksyen}" var="i" >
                            <s:option value="${i.kod}" >${i.nama}</s:option>
                        </c:forEach>
                    </s:select>
                </p>

            </c:if>

            <p>
                <label>Luas Sebenar :</label>


                <s:text name="luasT" id="luasT" value="${actionBean.hakmilikPermohonan.luasTerlibat}" formatPattern="###0.0000"/>&nbsp;
                <c:if test="${actionBean.hakmilikPermohonan.hakmilik ne null}">
                    <c:if test="${actionBean.hakmilikPermohonan.kodUnitLuas.nama eq 'Meter Persegi'}">mp</c:if>
                    <c:if test="${actionBean.hakmilikPermohonan.kodUnitLuas.nama eq 'Hektar'}">Ha</c:if>
                    <c:if test="${actionBean.hakmilikPermohonan.kodUnitLuas.nama eq 'Ekar '}">Ekar</c:if>
                </c:if>
                <c:if test="${actionBean.hakmilikPermohonan.hakmilik eq null}">
                    <%-- <s:text name="luasT" id="luasT" value="${actionBean.hakmilikPermohonan.luasBaru}"/>--%>
                    <c:if test="${line.hakmilikPermohonan.hakmilik.kodUnitLuas.nama eq 'Meter Persegi'}">mp</c:if>
                    <c:if test="${line.hakmilikPermohonan.hakmilik.kodUnitLuas.nama eq 'Hektar'}">Ha</c:if>                   
                    <s:select name="kodUnit" value="${actionBean.hakmilikPermohonan.kodUnitLuasBaru.kod}">
                        <s:option value="">Sila Pilih</s:option>
                        <s:option value="H">Hektar</s:option>
                        <s:option value="M">Meter Persegi</s:option>
                    </s:select>
                </c:if>
            </p>
            <p>
                <label>Luas Di Ambil :</label>
                <s:text name="luasTAmbil" id="luasTAmbil" value="${actionBean.permohonanPengambilan.luasAmbil}" formatPattern="###0.0000"/>
                <%-- <c:if test="${actionBean.hakmilikPermohonan.hakmilik eq null}">
                     <s:select name="kodUnitAmbil" value="${actionBean.kodUnitAmbil}">
                         <s:option value="">Sila Pilih</s:option>
                         <s:option value="H">Hektar</s:option>
                         <s:option value="M">Meter Persegi</s:option>
                     </s:select>
                 </c:if>
                 <c:if test="${actionBean.permohonanPengambilan eq null}">--%>
                <c:if test="${actionBean.hakmilikPermohonan.kodUnitLuas.nama eq 'Meter Persegi'}">mp</c:if>
                <c:if test="${actionBean.hakmilikPermohonan.kodUnitLuas.nama eq 'Hektar'}">Ha</c:if>
                <c:if test="${actionBean.hakmilikPermohonan.kodUnitLuas.nama eq 'Ekar '}">Ekar</c:if>
                <%--</c:if>--%>

            </p>

            <br>
            <c:if test="${actionBean.hakmilikPermohonan.hakmilik ne null}">

                <center>
                    <input type="checkbox" id="add1" name="add1" value="" onclick="copyAdd();"/> Sila Tick Jika Terdapat Perubahan Pada No Lot 
                </center>

            </c:if>

            <p>
                <label>&nbsp;</label>
                <s:button name="save" value="Simpan" class="btn" onclick="save1(this.name, this.form);" />
                <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
            </p>
        </fieldset>


    </s:form>
</div>