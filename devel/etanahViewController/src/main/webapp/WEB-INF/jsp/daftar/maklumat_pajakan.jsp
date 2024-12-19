<%-- 
    Document   : maklumat_pajakan
    Created on : Dec 3, 2009, 2:51:35 PM
    Author     : fikri
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/date.format.js"></script>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">

    $(document).ready(function(){
        var val = $('#selamanya').is(':checked');
        if (val) {
            $('.tempoh').hide();
            clearTempoh();
        } else {
            $('.tempoh').show();
        }       
    });

    function doCalcEndDate(id){
        var hari = parseInt($('.hari').val(),10);
        var bln = parseInt($('.bulan').val(),10);
        var thn = parseInt($('.tahun').val(),10);
        var kodUrusan = $('#kodUrusan').val();

        if($('#' +id).val() == '') {
            return;
        }
        
        if(isNaN(hari) && isNaN(bln) && isNaN(thn) && kodUrusan != 'PJLT'){
            alert('Sila Masukan Tempoh.');
            $('#' +id).val('');
            return;
        }
        if (hari == '0' && bln == '0' && thn == '0') return;
        var startDate = $('#' +id).val();        
        //manual process :: should find conclusion on this
        var y = parseInt(startDate.substring(6, 10),10);
        var m = parseInt(startDate.substring(3, 5),10);
        var d = parseInt(startDate.substring(0, 2),10);

        if(!isNaN(hari))
        {        
            d = d + hari;
            if (d ==0){
                m = m-1;
            }
        }
        
        if(!isNaN(bln)){
            m = m + bln;
            if(m > 12){
                y = y +1;
                m = m - 12;
                if (m == 2){
                    var isleap = (y % 4 == 0 && (y % 100 != 0 || y % 400 == 0));
                    if (d>=30){
                        if (isleap) {
                            d = 28;
                        } else {
                            d = 27;
                        }
                    }else if (d ==0 ){
                        if (isleap) {
                            d = 29;
                        } else {
                            d = 28;
                        }
                    }
                }
            }
        }
        
        if(!isNaN(thn))
        {
            y = y + thn;
            if (d ==0){
                m = m-1;
            }
        }

        if (d == 0 && m == 0) {
            y = y - 1;
        }              
        //y = y + thn;
        var endDate = new Date();
        var s =1;
        endDate.setDate(d);
        endDate.setMonth(m-1);
        endDate.setFullYear(y );
        endDate.setDate(endDate.getDate()-s);
       
        
        if (kodUrusan == 'TEN') {
            
            var moreThan3year = false;
            if (thn > 3) {
                moreThan3year = true;                
            } else if ( bln > 12 ){
                var v = bln / 12;
                thn = thn + v;
                if (thn > 3)moreThan3year = true;
            }           
                        
            if (moreThan3year) {
                alert('Melebihi 3 tahun. Urusan Tenansi tidak boleh melebihi 3 tahun!');
                $('#tkhTamat').val("");
                return;
            }
            
        }  
        
        $('#tkhTamat').val(endDate.format("dd/mm/yyyy"));
    }
    
    function hideTempoh() {
        var val = $('#selamanya').is(':checked');
        if (val) {
            $('.tempoh').hide();
            clearTempoh();
        } else {
            $('.tempoh').show();
        }
    }

    function clearTempoh() {
        $('#tahun').val('0');
        $('#bulan').val('0');
        $('#hari').val('0');        
        $('#tkhTamat').val('');
    }

    function doValid() {
        var check = $('#selamanya').is(':checked');
        if (!check) {
            var v = $('#tkhTamat').val();
            if (v == '') {
                alert ("Sila masukan tarikh tamat.");
                $('#trhTamat').focus();
                return false;
            }
        } else {
            var v = $('#tkhMula').val();
            if (v == '') {
                alert ("Sila masukan tarikh mula.");
                $('#tkhMula').focus();
                return false;
            }
        }
        
        var kodUrusan = $('#kodUrusan').val();
        
        if (kodUrusan == 'TEN') {
            
            var hari = parseInt($('.hari').val(),10);
            var bln = parseInt($('.bulan').val(),10);
            var thn = parseInt($('.tahun').val(),10);
            
            var moreThan3year = false;
            if (thn > 3) {
                moreThan3year = true;                
            } else if ( bln > 12 ){
                var v = bln / 12;
                thn = thn + v;
                if (thn > 3)moreThan3year = true;
            }           
                        
            if (moreThan3year) {
                alert('Melebihi 3 tahun. Urusan Tenansi tidak boleh melebihi 3 tahun!');
                $('#tkhTamat').val("");
                return false;
            }
            
        }  
        
//        $.get("${pageContext.request.contextPath}/daftar/pajakan?checkTempoh&idHakmilik="
//            + $('#hakmilik').val() + '&year='+ $('#tahun').val() + '&month=' + $('#bulan').val() + '&day=' + $('#hari').val(),
//        function(data){
//            if (data == 1) {
//                alert('Tempoh melebihi tempoh yang ada.');
//                $('#tahun').val('');
//                $('#bulan').val('');
//                $('#hari').val('');
//                return false;
//            }
//        });       
        
     var result = null;
     var scriptUrl = "${pageContext.request.contextPath}/daftar/pajakan?checkTempoh&idHakmilik="
            + $('#hakmilik').val() + '&year='+ $('#tahun').val() + '&month=' + $('#bulan').val() + '&day=' + $('#hari').val();
     $.ajax({
        url: scriptUrl,
        type: 'get',
        dataType: 'html',
        async: false,
        success: function(data) {
            if(data==1){
            alert('Tempoh melebihi tempoh yang ada.');
                $('#tahun').val('0');
                $('#bulan').val('0');
                $('#hari').val('0');
                         result = data;
                     }
                 }
             });

             if (result == 1) {
                 return false;
             }

             return true;
         }

    function reload (val) {
        doBlockUI();
        var url = '${pageContext.request.contextPath}/daftar/pajakan?showPajakanForm&idHakmilik=' + val;
        $.ajax({
            type:"GET",
            url : url,
            dataType : 'html',
            error : function (xhr, ajaxOptions, thrownError){
                alert("error=" + xhr.responseText);
                doUnBlockUI();
            },
            success : function(data){
                $('#page_div').html(data);
                $('.datepicker').datepicker();

                $("input:text").each( function(el) {
                    $(this).focus(function () {
                        $(this).addClass("focus");
                    } );
                    $(this).blur( function() {
                        $(this).removeClass("focus");
                        $(this).val( $(this).val().toUpperCase());
                    });
                });
                $("textarea").each( function(el) {
                    $(this).focus(function () {
                        $(this).addClass("focus");
                    });
                    $(this).blur( function() {
                        $(this).removeClass("focus");
                        $(this).val( $(this).val().toUpperCase());
                    });
                });

                doUnBlockUI();
            }
        });

    }
    
    //Added by Aizuddin for PJLT
    function doFunc(value) {
        if(value == "a")
        {
            $('#tempoh').hide();
            clearTempoh();
            $('#tahun').val('0');
            $('#bulan').val('6');
            $('#hari').val('1');
            doCalcEndDate('tkhMula');
        }
        if(value == "b")
        {
            clearTempoh();
            $('.tempoh').show();
            if(isNaN('.hari') && isNaN('.bulan') && isNaN('.tahun')){
                alert('Sila Masukan Tempoh Ditetapkan.');
                $('#' +id).val('');
                return;
            }                
        }
        if(value == "c")
        {
            $('.tempoh').hide();
            clearTempoh();
        }
        if(value == "d")
        {
            clearTempoh();
            $('.tempoh').show();
            if(isNaN('.hari') && isNaN('.bulan') && isNaN('.tahun')){                 
                alert('Sila Masukan Tempoh Diperintah Mahkamah.');
                $('#' +id).val('');
                return;
            }
        }
    }
    
</script>
<s:messages/>
<s:errors/>
<s:form beanclass="etanah.view.daftar.Pajakan">
    <s:hidden name="permohonanRujukanLuar.idRujukan"/>
    <s:hidden name="kodUrusan" id="kodUrusan" value="${actionBean.permohonan.kodUrusan.kod}"/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Senarai Hakmilik Terlibat</legend>
            <p>
                <label>Hakmilik :</label>
                <s:select name="idHakmilik" onchange="reload(this.value);" id="hakmilik">
                    <c:forEach items="${actionBean.hakmilikPermohonanList}" var="item" varStatus="line">
                        <s:option value="${item.hakmilik.idHakmilik}" style="width:400px">
                            ${item.hakmilik.idHakmilik} ( ${item.hakmilik.daerah.nama} - ${item.hakmilik.bandarPekanMukim.nama} )
                        </s:option>
                    </c:forEach>
                </s:select>
            </p>
            <br/>            
        </fieldset>
        <br/>
        <fieldset class="aras1">

            <legend>Maklumat
                <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'TEN' 
                              and actionBean.permohonan.kodUrusan.kod ne 'TENBT'
                              and actionBean.permohonan.kodUrusan.kod ne 'TENPT'
                              and actionBean.permohonan.kodUrusan.kod ne 'IS'
                              and actionBean.permohonan.kodUrusan.kod ne 'ISBLS' }">
                      Pajakan
                </c:if>
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'TEN' 
                              || actionBean.permohonan.kodUrusan.kod eq 'TENBT'
                              || actionBean.permohonan.kodUrusan.kod eq 'TENPT'}">
                      Tenansi
                </c:if>
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'IS' 
                              || actionBean.permohonan.kodUrusan.kod eq 'ISBLS'}">
                      Ismen
                </c:if>
            </legend>
            <c:if test="${fn:length(actionBean.hakmilikPermohonanList) > 1}">
                <p>
                    <label>&nbsp;</label>
                    <font color="red" size="2">
                        <input type="checkbox" name="copyToAll" value="1"/>
                        <em>Sila klik jika sama untuk semua hakmilik.</em>
                    </font>
                </p>
            </c:if>
            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'IS' || actionBean.permohonan.kodUrusan.kod eq 'ISBLS' }">
                <p>
                    <label for="nama pihak">Tujuan Ismen :</label>
                    <s:textarea name="permohonan.sebab" cols="50" rows="10"/>
                </p>
            </c:if>
                <%-- drop by faiz --%>
            <%--<c:if test="${actionBean.permohonan.kodUrusan.kod eq 'TENPT'}">
                <p>
                    <label for="maklumat petak">No Petak :</label>
                    <s:text name="hakmilik.noPetak"/>
                </p>
                <p>
                    <label for="maklumat petak">No Tingkat :</label>
                    <s:text name="hakmilik.noTingkat"/>
                </p>
                <p>
                    <label for="maklumat petak">No Bangunan :</label>
                    <s:text name="hakmilik.noBangunan"/>
                </p>
            </c:if>--%>
            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'IS' || actionBean.permohonan.kodUrusan.kod eq 'ISBLS'}">
                <c:set var="checked" value=""/>
                <c:if test="${selamanya}"><c:set var="checked" value="true"/></c:if>
                    <p>
                        <label>&nbsp;</label>
                    <s:checkbox name="selama" onclick="hideTempoh();" id="selamanya" checked="${checked}"/>Untuk Selama-lamanya
                </p>

            </c:if>
            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'TEN' 
                          || actionBean.permohonan.kodUrusan.kod eq 'TENBT'
                          || actionBean.permohonan.kodUrusan.kod eq 'PJLT'}">
                  <p class="no_fail">
                      <label>No Rujukan Fail :</label>
                      <s:text name="noFail" />
                  </p>
            </c:if>
            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PJLT'}">
                <p>
                    <label>Jangkamasa :</label>
                    <s:radio name="jangka" value="a" onclick="doFunc(value);" />6 Bulan
                    <s:radio name="jangka" value="b" onclick="doFunc(value);" />Bagi tempoh ditetapkan
                    <s:radio name="jangka" value="c" onclick="doFunc(value);"/>Selama-lamanya
                    <s:radio name="jangka" value="d" onclick="doFunc(value);"/>Tempoh bergantung kepada perintah mahkamah
                </p> 
            </c:if>            
                <p class="tempoh" id="tempoh">
                    <label>Tempoh :</label>
                    <s:text name="tahun" class="tahun" id="tahun" value="${thn}" onchange="doCalcEndDate('tkhMula');"/> tahun &nbsp;
                    <s:text name="bulan" class="bulan" id="bulan" value="${bln}" onchange="doCalcEndDate('tkhMula');"/> bulan &nbsp;
                    <s:hidden name="hari" class="hari" id="hari" value="${hari}" onchange="doCalcEndDate('tkhMula');"/>
                </p> 
            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PJLT'}">
                <p>
                <label for="nama pihak">Tarikh Tamat Pajakan Lama :</label>
                <s:text name="tkhLama" id="tkhLama" readonly="true"/>
            </p>
            </c:if>
            <p>
                <label for="nama pihak">Tarikh Mula :</label>
                <s:text name="tkhMula"  id="tkhMula" class="datepicker"
                        onchange="doCalcEndDate(this.id);"/>
            </p>
            <p class="tempoh">
                <label for="nama pihak">Tarikh Tamat :</label>
                <s:text name="tkhTamat" class="datepicker" formatType="date" formatPattern="dd/MM/yyyy" id="tkhTamat"/>
            </p>            
            <p>
                <label>&nbsp;</label>
                <s:button name="savePajakan" value="Simpan" onclick="if ( doValid() )doSubmit(this.form, this.name, 'page_div')" class="btn"/>
            </p>
        </fieldset>
    </div>
</s:form>
