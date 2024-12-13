<%-- 
    Document   : Borang E
    Created on : 23-Nov-2009, 11:55:43
    Author     : nordiyana
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/tablecloth.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript">
    $(document).ready( function(){
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
    });

    function refreshPagePenerimaanBorang(){
        var url = '${pageContext.request.contextPath}/pengambilan/penerimaan_borang?refreshpage';
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }

    function updateDetails(h){
        var url = '${pageContext.request.contextPath}/pengambilan/penerimaan_borang?editDetails&rowCount='+h;
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }

    function validation() {
        var bantahanPenilai  = $("#bantahanPenilai ").val();
        var sebabTangguh  = $("#sebabTangguh ").val();
        var tarikhUlangan  = $("#tarikhUlangan ").val();
        var tarikhSuratPenilai  = $("#tarikhSuratPenilai ").val();
        var count  = $("#count ").val();

        for(var i=1;i<=count;i++){
            var bantahanPenilai = $("#bantahanPenilai"+(i - 1)).val();
            var sebabTangguh = $("#sebabTangguh"+(i - 1)).val();
            var tarikhUlangan = $("#tarikhUlangan"+(i - 1)).val();
            var tarikhSuratPenilai = $("#tarikhSuratPenilai"+(i - 1)).val();
          

            if(bantahanPenilai == ""){
                alert('Sila masukkan " No Resit Deposit " terlebih dahulu.');
                $("#tarikhBicara"+(i - 1)).focus();
                return false;
            }
            if(sebabTangguh == ""){
                alert('Sila masukkan " No Resit Fees " terlebih dahulu.');
                $("#sebabTangguh"+(i - 1)).focus();
                return false;
            }
            if(tarikhUlangan == ""){
                alert('Sila pilih " Tarikh Bayaran Deposit " terlebih dahulu.');
                $("#tarikhUlangan"+(i - 1)).focus();
                return false;
            }
            if(tarikhSuratPenilai == ""){
                alert('Sila pilih " Tarikh Bayaran Fees " terlebih dahulu.');
                $("#tarikhSuratPenilai"+(i - 1)).focus();
                return false;
            }
        }
        return true;
    }
</script>

<s:form beanclass="etanah.view.stripes.pengambilan.PenerimaanBorangActionBean">

    <s:messages/>
    <div>
        <fieldset class="aras1">
            <legend align="left">
                <b>Sila masukkan maklumat berkaitan diruang yang disediakan.</b>
            </legend>
        </fieldset>
    </div>
    <br>
    <div  id="hakmilik_details">
        <fieldset class="aras1"><br/>
            <legend align="center">
                <b>RESIT BAYARAN</b>
            </legend><br/>
            <p align="center">
                <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" pagesize="6" cellpadding="0" cellspacing="0"
                               requestURI="/pengambilan/penerimaan_borang" id="line">

                    <c:if test="${line_rowNum-1 == 0}">
                        <display:column title="Resit Bayaran Deposit">
                            <label style="text-align:right;width:30%; padding: 0em 0em">Tarikh Resit  :</label>
                            <s:text style="text-align:left;width:30%" name="tarikhUlangan[${line_rowNum - 1}]"  class="datepicker" formatType="date" formatPattern="dd/MM/yyyy" id="tarikhUlangan${line_rowNum - 1}"/>
                            <s:hidden name="count" value="${fn:length(actionBean.hakmilikPermohonanList)}" id="count"/>
                        </display:column>

                        <display:column >
                            <label style="text-align:right;width:30%; padding: 0em 0em">Resit No :</label>
                            <s:text style="text-align:left;width:30%" name="bantahanPenilai[${line_rowNum - 1}]" id="bantahanPenilai${line_rowNum - 1}" value="bantahanPenilai" class="normal_text"/></display:column>

                    </c:if>
                </display:table>

                <br/><br/><br/>
                <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" pagesize="6" cellpadding="0" cellspacing="0"
                               requestURI="/pengambilan/penerimaan_borang" id="line">
                    <c:if test="${line_rowNum-1 == 0}">
                        <display:column title="Resit Bayaran Fee">
                            <label style="text-align:right;width:30%; padding: 0em 0em">Tarikh Resit  :</label>
                            <s:text style="text-align:left;width:30%" name="tarikhSuratPenilai[${line_rowNum - 1}]"  class="datepicker" formatType="date" formatPattern="dd/MM/yyyy" id="tarikhSuratPenilai[${line_rowNum - 1}"/>
                            <s:hidden name="count" value="${fn:length(actionBean.hakmilikPermohonanList)}" id="count"/>
                        </display:column>
                        <display:column >
                            <label style="text-align:right;width:30%; padding: 0em 0em">Resit No  :</label>
                            <s:text style="text-align:left;width:30%" name="sebabTangguh[${line_rowNum - 1}]" id="sebabTangguh${line_rowNum - 1}" value="sebabTangguh" class="normal_text"/></display:column>
                    </c:if>
                </display:table><br><br>
                <%--<s:button name="simpanHakmilik" id="save" value="Simpan" class="btn" onclick="if(validation())doSubmit(this.form, this.name, 'page_div')"/>--%>
                <s:button name="simpanHakmilik2" id="save" value="Simpan" class="btn" onclick="if(validation())doSubmit(this.form, this.name, 'page_div')"/>

                <br/>
                <br/>
                <br/>

        </fieldset>
    </div>
    <%--<div>
        <fieldset class="aras1">
            <legend align="center">
                <b>SEJARAH</b>
            </legend><br/>
            <c:if test="${edit}">
            <p>
                <label for="ID_Hakmilik">ID Hakmilik :</label>${actionBean.hakmilikPerbicaraan.hakmilikPermohonan.hakmilik.idHakmilik}&nbsp;
            </p>

            <c:if test="${actionBean.hakmilikPerbicaraan.catatan eq 'Tangguh'}">
            <c:if test="${actionBean.hakmilikPermohonan.id.hakmilikPerbicaraan.catatan eq 'Tangguh'}">
                <p>
                    <label for="Nama_Pihak_Terlibat">Nama Pihak Terlibat :</label>
                    <c:set value="1" var="count"/>
                    <c:forEach items="${actionBean.hakmilikPerbicaraan.senaraiKehadiran}" var="senarai">
                        <c:if test="${count ne 1}">
                        <label> &nbsp;</label>
                        </c:if>
                        <c:out value="${count}"/>)&nbsp;
                        <c:out value="${senarai.nama}"/><br>
                        <c:set value="${count + 1}" var="count"/>
                    </c:forEach>
                </p>
            </c:if>
            <c:if test="${actionBean.hakmilikPerbicaraan.catatan eq 'Bantahan' && actionBean.perbicaraanKehadiran.bantahElektrik eq 1}">
                <c:if test="${actionBean.perbicaraanKehadiran.bantahElektrik eq '1'}">
                <p>
                    <label for="Nama_Pihak_Terlibat">Nama Pihak Terlibat :</label>
                    <c:set value="1" var="count"/>
                    <c:forEach items="${actionBean.hakmilikPerbicaraan.senaraiKehadiran}" var="senarai">
                        <c:if test="${count ne 1}">
                        <label> &nbsp;</label>
                        </c:if>
                        <c:out value="${count}"/>)&nbsp;
                        <c:out value="${senarai.nama}"/><br>
                        <c:set value="${count + 1}" var="count"/>
                    </c:forEach>
                </p>
                </c:if>
            </c:if>
            <p>
                <label for="Tarikh_Bicara">Tarikh Bicara :</label>${actionBean.hakmilikPerbicaraan.tarikhBicara}&nbsp;
            </p>
            <p>
                <label for="Lokasi">Lokasi :</label>${actionBean.hakmilikPerbicaraan.lokasiBicara}&nbsp;
            </p>
            <p>
                <label for="Sebab">Keputusan Perbicaraan :</label>
                <c:if test="${actionBean.hakmilikPerbicaraan.catatan eq 'Tangguh'}">Penangguhan </c:if>
                <c:if test="${actionBean.hakmilikPerbicaraan.catatan eq 'Bantahan'}">Pertikaian </c:if>
            </p>
            </c:if>
        </fieldset>
    </div>--%>
</s:form>
