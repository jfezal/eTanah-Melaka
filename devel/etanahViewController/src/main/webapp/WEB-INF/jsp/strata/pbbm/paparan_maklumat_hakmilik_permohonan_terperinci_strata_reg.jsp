<%--
    Document   : paparan_maklumat_hakmilik_permohonan_terperinci_strata_reg
    Created on : 27 Aug 2012, 3:41:04 PM
    Author     : murali    
--%>
<div id="maklumatHakmilik">
    <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
    <%@ page contentType="text/html;charset=windows-1252"%>
    <%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
    <%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
    <%-- <script type="text/javascript"
             src="<%= request.getContextPath()%>/scripts/jquery-1.3.2.min.js"></script>--%>

    <script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery.bestupper.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/fraction.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
    <script type="text/javascript">
        
        $(document).ready( function(){
            //filterKodSeksyen();
            //filterKodGunaTanah();
            $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'}); 
        });

        /*function p(v){
            $.blockUI({
                message: $('#displayBox'),
                css: {
                    top:  ($(window).height() - 50) /2 + 'px',
                    left: ($(window).width() - 50) /2 + 'px',
                    width: '50px'
                }
            });

            $.get("${pageContext.request.contextPath}/pendaftaran/kemasukan_perincian_hakmilik?idHakmilik="+v,
            function(data){
                //alert(data);
                $("#perincianHakmilik").show();
                $("#perincianHakmilik").html(data);
                $.unblockUI();
            });
        }*/

        function kiraCukaiKelompok(id,i){
            $.blockUI({
                message: $('#displayBox'),
                css: {
                    top:  ($(window).height() - 50) /2 + 'px',
                    left: ($(window).width() - 50) /2 + 'px',
                    width: '50px'
                }
            });
            //var kodTanah = $("#kodTanah").val();
            //var kodBpm = $("#kodBpm").val();
            var kodUOM = $("#kodUOM"+i).val();
            var luas = $("#luas"+i).val();
            //alert(id);
            //alert(kodUOM);
            //alert(luas);
            //var kodRizab = $("#kodRizab").val();
            //alert(kodRizab);
            //var q = $(f).formSerialize();
            $.post('${pageContext.request.contextPath}/strata/maklumat_hakmilik_permohonan?kiraCukaiKelompok&idHakmilik='+id+'&kodUOM='+kodUOM+'&luas='+luas,
            function(data){
                if(data != ''){

                    $('#cukai'+i).val(convert(data,'cukai'+i));
                    $.unblockUI();
                }
            }, 'html');
        }
        function removeMultipleMohonHakmilik(){
            if (confirm('Adakah anda pasti untuk hapus hakmilik ini')){

                var param = '';
                $('.remove2').each(function(index){
                    var a = $('#checkbox'+index).is(":checked");
                    if(a) {
                        param = param + '&idMohonHakmilik=' + $('#checkbox'+index).val();
                    }
                });

                if(param == ''){
                    alert('Sila Pilih Hakmilik terlebih dahulu.');
                    return;
                }

                var url = '${pageContext.request.contextPath}/pendaftaran/kemasukan_perincian_hakmilik?deleteMultipleMohonHakmilik'+param;
                $.get(url,
                function(data){
                    $('#page_div').html(data);
                }, 'html');
            }
        }

        function selectAll(a){
            var size = '${fn:length(actionBean.hakmilikList)}';
            
            for (i = 0; i < size; i++){
                var c = document.getElementById("checkbox" + i);
                
                if (c == null) break;
                c.checked = a.checked;
            }
        }
        function perincianTanah(v,w){
            $.blockUI({
                message: $('#displayBox'),
                css: {
                    top:  ($(window).height() - 50) /2 + 'px',
                    left: ($(window).width() - 50) /2 + 'px',
                    width: '50px'
                }
            });
            $.get("${pageContext.request.contextPath}/strata/permohonanStrata?showForm&idHakmilikPihakBerkepentingan="+v+"&idHakmilik="+w,
            function(data){
                $("#perincianTanah").show();
                $("#perincianTanah").html(data);
                $.unblockUI();
            });
        }
        function perincianHakmilik(v){
            $.blockUI({
                message: $('#displayBox'),
                css: {
                    top:  ($(window).height() - 50) /2 + 'px',
                    left: ($(window).width() - 50) /2 + 'px',
                    width: '50px'
                }
            });                    

            $.get("${pageContext.request.contextPath}/strata/maklumat_hakmilik_permohonan?showHakmilikStrataReg&idHakmilik="+v,
            function(data){                
                $("#perincianHakmilik").show();
                $("#perincianHakmilik").html(data);
                $.unblockUI();
            });

        }
            
    </script>
    <img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>
    <s:form beanclass="etanah.view.strata.StrataMaklumatHakmilikPermohonanActionBean">
        <div class="subtitle displaytag">            
            <s:messages/>

            <br /><br />
            <center><p><font color="blue"><b>Jumlah Unit Syer: <%--<s:text name="hakmilik.jumlahUnitSyer" readonly="true" size="8"/>--%><s:text name="jumlahUnitSyor" readonly="true" size="8"/>
                        </b></font></p></center>
            <br /> 
            <fieldset class="aras1">                
                <legend>Maklumat Hakmilik Baru</legend> <br/><br/>
                <c:if test="${fn:length(actionBean.hakmilikPermohonanList) > 0}">
                    <p><label></label>
                        <s:hidden name="sizeHakmilikPermohonan" value="${fn:length(actionBean.hakmilikPermohonanList)}" id="sizeHakmilikPermohonan"/>
                        <display:table class="tablecloth" style="width:100%;" requestURI="${pageContext.request.contextPath}/strata/maklumat_hakmilik_permohonan" name="${actionBean.hakmilikPermohonanList}" cellpadding="0" cellspacing="0" pagesize="150" id="line">
                            <display:column title="No"><center>${line_rowNum}</center></display:column>
                       <%-- <c:if test="${actionBean.stageId eq 'kemasukan'}">--%>
                        <display:column title="ID Hakmilik"><center><a href="#" onclick="perincianHakmilik('${line.hakmilik.idHakmilik}');return false;"><c:if test="${line.hakmilik.noPelan eq null}"><font color="red">${line.hakmilik.idHakmilik}</font></c:if><c:if test="${line.hakmilik.noPelan ne null}">${line.hakmilik.idHakmilik}</c:if></a></center>
                            <s:hidden name="hiddenIdHakmilik" id="hiddenIdHakmilik${line_rowNum-1}" value="${line.hakmilik.idHakmilik}"/>
                        </display:column>
                        <%--</c:if>--%>
                        <%-- <c:if test="${actionBean.stageId ne 'kemasukan'}">
                        <display:column title="ID Hakmilik"><center>${line.hakmilik.idHakmilik}</center>
                            <s:hidden name="hiddenIdHakmilik" id="hiddenIdHakmilik${line_rowNum-1}" value="${line.hakmilik.idHakmilik}"/>
                        </display:column>
                        </c:if>--%>
                        <c:if test="${actionBean.kodNegeri eq '04'}">
                        <display:column title="No Bangunan">
                            <center>${line.hakmilik.noBangunan}</center>
                        </display:column>
                        <display:column title="No Tingkat">
                            <center>${line.hakmilik.noTingkat}</center>
                        </display:column>
                        <display:column title="No Petak">
                            <center>${line.hakmilik.noPetak}</center>
                        </display:column>
                        </c:if>  
                            
                        <c:if test="${actionBean.kodNegeri eq '05'}">
                        <display:column title="No Bangunan">
                            <c:if test="${line.hakmilik.kodKategoriBangunan.kod ne 'L'}">
                                <center>${line.hakmilik.noBangunan}</center>
                            </c:if>                            
                        </display:column>
                        <display:column title="No Tingkat">
                            <c:if test="${line.hakmilik.kodKategoriBangunan.kod ne 'L'}">
                               <center>${line.hakmilik.noTingkat}</center> 
                            </c:if>                           
                        </display:column>
                        <display:column title="No Petak">
                            <c:if test="${line.hakmilik.kodKategoriBangunan.kod ne 'L'}">
                             <center>${line.hakmilik.noPetak}</center>   
                            </c:if>
                            <c:if test="${line.hakmilik.kodKategoriBangunan.kod eq 'L'}">
                             <center>L${line.hakmilik.noPetak}</center>   
                            </c:if>
                        </display:column>
                        </c:if>  
                        <%--display:column title="Luas / Unit">
                            <center><s:text name="hakmilikPermohonanList[${line_rowNum-1}].hakmilik.luas" id="luas${line_rowNum-1}" value="${line.hakmilik.luas}" formatPattern="###0.0000" readonly="true" size="15" onchange="kiraCukaiKelompok('${line.hakmilik.idHakmilik}','${line_rowNum-1}');"/>
                                <s:text name="hakmilikPermohonanList[${line_rowNum-1}].hakmilik.kodUnitLuas.nama" id="kodUOM${line_rowNum-1}" value="${line.hakmilik.kodUnitLuas.nama}" readonly="true" size="25" onchange="kiraCukaiKelompok('${line.hakmilik.idHakmilik}','${line_rowNum-1}');"/>
                            </center>
                        </display:column--%>
                    </display:table>
                    &nbsp;                   
                </c:if>               
                <br>
            </fieldset>
        </div>
    </s:form>
    <div id="perincianHakmilik">
    </div>
</div>



